package com.jvt.timetracker.dto;

import lombok.Data;
import java.time.OffsetDateTime;

@Data
public class ProjectResponseDTO {
    private Long id;
    private String name;
    private String description;
    private Long userId;
    private String userName;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
} 