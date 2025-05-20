package com.jvt.timetracker.dto;

import lombok.Data;
import java.time.OffsetDateTime;

@Data
public class WorkLogDTO {
    private Long id;
    private Long projectId;
    private OffsetDateTime startTime;
    private OffsetDateTime endTime;
    private Long durationMinutes;
    private String description;
    private boolean deleted;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
} 