package com.javanauta.ts.bff.infrastructure.client;

import com.javanauta.ts.bff.business.dto.TaskDTO;
import com.javanauta.ts.bff.business.enums.NotificationStatusEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "task-scheduler", url = "${task.url}")
public interface TaskClient {

    @PostMapping
    TaskDTO saveTask(@RequestBody TaskDTO taskDTO, @RequestHeader("Authorization") String token);

    @GetMapping("/events")
    List<TaskDTO> findTaskListByPeriod(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime initialDateTime, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime finalDateTime, @RequestHeader("Authorization") String token);

    @GetMapping
    List<TaskDTO> findTaskListByUserEmail(@RequestHeader("Authorization") String token);

    @DeleteMapping
    void deleteTaskById(@RequestParam("id") String id, @RequestHeader("Authorization") String token);

    @PatchMapping
    TaskDTO modifyTaskStatusById(@RequestParam("status") NotificationStatusEnum notificationStatusEnum, @RequestParam("id") String id, @RequestHeader("Authorization") String token);

    @PutMapping
    TaskDTO updateTaskById(@RequestBody TaskDTO taskDTO, @RequestParam("id") String id, @RequestHeader("Authorization") String token);
}
