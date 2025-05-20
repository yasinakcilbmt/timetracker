package com.jvt.timetracker.dto;

import lombok.Data;
import java.time.OffsetDateTime;

@Data
public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
} 