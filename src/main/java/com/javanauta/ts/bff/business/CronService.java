package com.javanauta.ts.bff.business;

import com.javanauta.ts.bff.business.dto.in.UserLoginRequestDTO;
import com.javanauta.ts.bff.business.dto.out.TaskDTOResponse;
import com.javanauta.ts.bff.business.enums.NotificationStatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
//@Slf4j
public class CronService {

    @Value("${ts.cron.user.email}")
    private String email;

    @Value("${ts.cron.user.password}")
    private String password;

    private final UserService userService;
    private final TaskService taskService;
    private final NotifierService notifierService;

    @Scheduled(cron = "${ts.cron.frequency}")
    public void findTasksInNextNotificationPeriod(){
        long notificationPeriod = 1L; // in hours - currently hard coded but meant to be an user setting later
        String token = loginCronUser(createUserLoginRequestDTO());

        Instant nowTime = Instant.now();

        List<TaskDTOResponse> tasksList = taskService.findTaskByTimePeriod(nowTime, nowTime.plus(notificationPeriod, ChronoUnit.HOURS), token);

        tasksList.forEach(
                task -> {
                    notifierService.sendEmail(task);
                    taskService.modifyTaskStatusById(NotificationStatusEnum.NOTIFIED, task.getId(), token);
                }
        );
    }

    public String loginCronUser(UserLoginRequestDTO userLoginRequestDTO) {
        return userService.loginUser(userLoginRequestDTO);
    }

    public UserLoginRequestDTO createUserLoginRequestDTO() {
        return UserLoginRequestDTO.builder()
                .email(email)
                .password(password)
                .build();
    }
}
