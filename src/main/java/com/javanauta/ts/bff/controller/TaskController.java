package com.javanauta.ts.bff.controller;

import com.javanauta.ts.bff.business.TaskService;
import com.javanauta.ts.bff.business.dto.in.TaskDTORequest;
import com.javanauta.ts.bff.business.dto.out.TaskDTOResponse;
import com.javanauta.ts.bff.business.enums.NotificationStatusEnum;
import com.javanauta.ts.bff.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
@Tag(name = "task", description = "Creation, update and deletion of Tasks")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    @Operation(summary = "Create task", description = "Creates a new task")
    @ApiResponse(responseCode = "200", description = "Task successfully created")
    @ApiResponse(responseCode = "400", description = "Invalid time zone ID")
    @ApiResponse(responseCode = "401", description = "Unauthorized user")
    @ApiResponse(responseCode = "500", description = "Server error")
    public ResponseEntity<TaskDTOResponse> saveTask(@RequestBody TaskDTORequest taskDTORequest, @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(taskService.saveTask(token, taskDTORequest));
    }

    @GetMapping("/events")
    @Operation(summary = "Get tasks by time period", description = "Gets tasks within a specific date and time period")
    @ApiResponse(responseCode = "200", description = "Tasks successfully found")
    @ApiResponse(responseCode = "401", description = "Unauthorized user")
    @ApiResponse(responseCode = "500", description = "Server error")
    public ResponseEntity<List<TaskDTOResponse>> findTaskListByPeriod(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant initialDateTime, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant finalDateTime, @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(taskService.findTaskByTimePeriod(initialDateTime, finalDateTime, token));
    }

    @GetMapping
    @Operation(summary = "Get tasks by user email", description = "Gets all tasks of an user identified by their email")
    @ApiResponse(responseCode = "200", description = "Tasks successfully found")
    @ApiResponse(responseCode = "401", description = "Unauthorized user")
    @ApiResponse(responseCode = "404", description = "User not found")
    @ApiResponse(responseCode = "500", description = "Server error")
    public ResponseEntity<List<TaskDTOResponse>> findTaskListByUserEmail(@RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(taskService.findTaskByUserEmail(token));
    }

    @DeleteMapping
    @Operation(summary = "Delete task", description = "Deletes a task identified by its ID")
    @ApiResponse(responseCode = "200", description = "Task successfully deleted")
    @ApiResponse(responseCode = "401", description = "Unauthorized user")
    @ApiResponse(responseCode = "404", description = "Task not found")
    @ApiResponse(responseCode = "500", description = "Server error")
    public ResponseEntity<Void> deleteTaskById(@RequestParam("id") String id, @RequestHeader(name = "Authorization", required = false) String token){
        taskService.deleteTaskById(id, token);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    @Operation(summary = "Modify task status", description = "Modify the status of a task identified by its ID")
    @ApiResponse(responseCode = "200", description = "Task status successfully modified")
    @ApiResponse(responseCode = "401", description = "Unauthorized user")
    @ApiResponse(responseCode = "404", description = "Task not found")
    @ApiResponse(responseCode = "500", description = "Server error")
    public ResponseEntity<TaskDTOResponse> modifyTaskStatusById(@RequestParam("status") NotificationStatusEnum notificationStatusEnum, @RequestParam("id") String id, @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(taskService.modifyTaskStatusById(notificationStatusEnum, id, token));
    }

    @PutMapping
    @Operation(summary = "Update task", description = "Update the data of a task identified by its ID")
    @ApiResponse(responseCode = "200", description = "Task successfully updated")
    @ApiResponse(responseCode = "400", description = "Invalid time zone ID")
    @ApiResponse(responseCode = "401", description = "Unauthorized user")
    @ApiResponse(responseCode = "404", description = "Task not found")
    @ApiResponse(responseCode = "500", description = "Server error")
    public ResponseEntity<TaskDTOResponse> updateTaskById(@RequestBody TaskDTORequest taskDTORequest, @RequestParam("id") String id, @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(taskService.updateTaskById(taskDTORequest, id, token));
    }
}
