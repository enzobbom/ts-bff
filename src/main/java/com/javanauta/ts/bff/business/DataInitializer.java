package com.javanauta.ts.bff.business;

import com.javanauta.ts.bff.business.dto.in.UserDTORequest;
import com.javanauta.ts.bff.infrastructure.exception.ConflictException;
import feign.FeignException;
import feign.RetryableException;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer {

    @Value("${ts.cron.user.email}")
    private String cronUserEmail;

    @Value("${ts.cron.user.password}")
    private String cronUserPassword;

    private final UserService userService;

    @EventListener(ApplicationReadyEvent.class)
    @Async
    public void init() {
        RetryConfig config = RetryConfig.custom()
                .maxAttempts(5)
                .waitDuration(Duration.ofSeconds(3))
                .retryExceptions(RetryableException.class, FeignException.ServiceUnavailable.class)
                .build();

        Retry retry = Retry.of("create-cron-user", config);

        Runnable retryableTask = Retry.decorateRunnable(
                retry,
                this::createCronUser
        );

        try {
            retryableTask.run();
        } catch (Exception e) {
            log.error("Failed to create cron user after retries", e);
        }
    }

    private void createCronUser() {
        UserDTORequest user = new UserDTORequest();
        user.setEmail(cronUserEmail);
        user.setPassword(cronUserPassword);

        try {
            // Workaround: saveUser() will error with a ConflictException in case the user already exists
            userService.saveUser(user);
            log.info("Cron user successfully created for the first time");

        } catch (ConflictException e) {
            // FeignException.Conflict will already have been converted to local ConflictException
            log.info("Cron user already existed");

        } catch (RetryableException e) {
            // Handle network issues or retriable errors
            log.error("Network problem during Feign call: {}", e.getMessage());
            throw e;

        } catch (Exception e) {
            log.error("Unexpected error when creating Cron user: {}", e.getMessage());
            throw e;
        }
    }
}
