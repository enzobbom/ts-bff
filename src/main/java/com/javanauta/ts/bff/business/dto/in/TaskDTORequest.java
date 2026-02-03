package com.javanauta.ts.bff.business.dto.in;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDTORequest {

    private String name;
    private String description;
    private Instant dueDateTime;
    private String timeZoneId;
}
