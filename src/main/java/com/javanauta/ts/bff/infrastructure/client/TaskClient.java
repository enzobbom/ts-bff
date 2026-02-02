package com.javanauta.ts.bff.infrastructure.client;

import com.javanauta.ts.bff.business.dto.in.TaskDTORequest;
import com.javanauta.ts.bff.business.dto.out.TaskDTOResponse;
import com.javanauta.ts.bff.business.enums.NotificationStatusEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "task-scheduler", url = "${ts.task.service.uri}")
public interface TaskClient {

    @PostMapping
    TaskDTOResponse saveTask(@RequestBody TaskDTORequest taskDTORequest, @RequestHeader("Authorization") String token);

    @GetMapping("/events")
    List<TaskDTOResponse> findTaskListByPeriod(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime initialDateTime, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime finalDateTime, @RequestHeader("Authorization") String token);

    @GetMapping
    List<TaskDTOResponse> findTaskListByUserEmail(@RequestHeader("Authorization") String token);

    @DeleteMapping
    void deleteTaskById(@RequestParam("id") String id, @RequestHeader("Authorization") String token);

    @PatchMapping
    TaskDTOResponse modifyTaskStatusById(@RequestParam("status") NotificationStatusEnum notificationStatusEnum, @RequestParam("id") String id, @RequestHeader("Authorization") String token);

    @PutMapping
    TaskDTOResponse updateTaskById(@RequestBody TaskDTORequest taskDTORequest, @RequestParam("id") String id, @RequestHeader("Authorization") String token);
}
