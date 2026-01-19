package com.javanauta.ts.bff.business;

import com.javanauta.ts.bff.business.dto.in.TaskDTORequest;
import com.javanauta.ts.bff.business.dto.out.TaskDTOResponse;
import com.javanauta.ts.bff.business.enums.NotificationStatusEnum;
import com.javanauta.ts.bff.infrastructure.client.TaskClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskClient taskClient;

    public TaskDTOResponse saveTask(String token, TaskDTORequest taskDTORequest) {
        return taskClient.saveTask(taskDTORequest, token);
    }

    public List<TaskDTOResponse> findTaskByTimePeriod(LocalDateTime initialDateTime, LocalDateTime finalDateTime, String token) {
        return taskClient.findTaskListByPeriod(initialDateTime, finalDateTime, token);
    }

    public List<TaskDTOResponse> findTaskByUserEmail(String token) {
        return taskClient.findTaskListByUserEmail(token);
    }

    public void deleteTaskById(String id, String token) {
        taskClient.deleteTaskById(id, token);
    }

    public TaskDTOResponse modifyTaskStatusById (NotificationStatusEnum notificationStatusEnum, String id, String token) {
        return taskClient.modifyTaskStatusById(notificationStatusEnum, id, token);
    }

    public TaskDTOResponse updateTaskById (TaskDTORequest taskDTORequest, String id, String token) {
        return taskClient.updateTaskById(taskDTORequest, id, token);
    }
}
