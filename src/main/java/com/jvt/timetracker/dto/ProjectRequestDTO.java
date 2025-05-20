package com.jvt.timetracker.dto;

import lombok.Data;

@Data
public class ProjectRequestDTO {
    private String name;
    private String description;
    private Long userId;
} 