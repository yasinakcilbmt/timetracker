package com.jvt.timetracker.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class ReportDTO {
    private Long userId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long totalMinutes;
    private List<String> details; // örnek: her günün toplamı gibi
} 