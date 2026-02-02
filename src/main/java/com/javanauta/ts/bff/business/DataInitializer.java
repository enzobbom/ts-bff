package com.javanauta.ts.bff.business;


import com.javanauta.ts.bff.business.dto.in.UserDTORequest;
import com.javanauta.ts.bff.infrastructure.exception.ConflictException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
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
            // Workaround: saveUser() will error in case the user already exists
            userService.saveUser(user);
            log.info("Cron user created for the first time.");
        } catch (ConflictException ignored) {
            log.info("Cron user already existed.");
        }
    }
}
