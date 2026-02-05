package com.javanauta.ts.bff.business;


import com.javanauta.ts.bff.business.dto.in.UserDTORequest;
import com.javanauta.ts.bff.infrastructure.exception.ConflictException;
import feign.FeignException;
import feign.RetryableException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {

    @Value("${ts.cron.user.email}")
    private String cronUserEmail;

    @Value("${ts.cron.user.password}")
    private String cronUserPassword;

    private final UserService userService;

    @Override
    public void run(ApplicationArguments args) {
        UserDTORequest user = new UserDTORequest();
        user.setEmail(cronUserEmail);
        user.setPassword(cronUserPassword);

        try {
            // Workaround: saveUser() will error with a ConflictException in case the user already exists
            userService.saveUser(user);
            log.info("Cron user created for the first time.");

        } catch (ConflictException e) {
            // FeignException.Conflict will already have been converted to local ConflictException
            log.info("Cron user already existed");

        } catch (RetryableException e) {
            // Handle network issues or retriable errors
            log.error("Network problem during Feign call: {}", e.getMessage());

        } catch (Exception e) {
            // Catches any other types of errors/bugs
            log.error("Unexpected error during Feign call: {}", e.getMessage());
        }
    }
}
