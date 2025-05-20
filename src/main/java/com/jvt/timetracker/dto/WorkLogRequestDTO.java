package com.jvt.timetracker.dto;

import lombok.Data;
import java.time.OffsetDateTime;

@Data
public class WorkLogRequestDTO {
    private Long projectId;
    private OffsetDateTime startTime;
    private OffsetDateTime endTime;
    private String description;
} 