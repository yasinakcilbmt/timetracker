package com.jvt.timetracker.dto;

import lombok.Data;
import java.time.OffsetDateTime;

@Data
public class WorkLogResponseDTO {
    private Long id;
    private Long projectId;
    private String projectName;
    private Long userId;
    private String userName;
    private OffsetDateTime startTime;
    private OffsetDateTime endTime;
    private Long durationMinutes;
    private String description;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
} 