package com.jvt.timetracker.dto;

import lombok.Data;
import java.time.OffsetDateTime;

@Data
public class ProjectDTO {
    private Long id;
    private String name;
    private String description;
    private Long userId;
    private boolean deleted;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
} 