package com.javanauta.ts.bff.business;

import com.javanauta.ts.bff.business.dto.TaskDTO;
import com.javanauta.ts.bff.business.enums.NotificationStatusEnum;
import com.javanauta.ts.bff.infrastructure.client.TaskClient;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskClient taskClient;

    public TaskDTO saveTask(String token, TaskDTO taskDTO) {
        return taskClient.saveTask(taskDTO, token);
    }

    public List<TaskDTO> findTaskByTimePeriod(LocalDateTime initialDateTime, LocalDateTime finalDateTime, String token) {
        return taskClient.findTaskListByPeriod(initialDateTime, finalDateTime, token);
    }

    public List<TaskDTO> findTaskByUserEmail(String token) {
        return taskClient.findTaskListByUserEmail(token);
    }

    public void deleteTaskById(String id, String token) {
        taskClient.deleteTaskById(id, token);
    }

    public TaskDTO modifyTaskStatusById (NotificationStatusEnum notificationStatusEnum, String id, String token) {
        return taskClient.modifyTaskStatusById(notificationStatusEnum, id, token);
    }

    public TaskDTO updateTaskById (TaskDTO taskDTO, String id, String token) {
        return taskClient.updateTaskById(taskDTO, id, token);
    }
}
