package com.jvt.timetracker.service;

import com.jvt.timetracker.dto.ReportDTO;
import com.jvt.timetracker.model.WorkLog;
import com.jvt.timetracker.repository.WorkLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final WorkLogRepository workLogRepository;

    public ReportDTO getDailyReport(Long userId, LocalDate date) {
        OffsetDateTime start = date.atStartOfDay().atOffset(OffsetDateTime.now().getOffset());
        OffsetDateTime end = start.plusDays(1);
        List<WorkLog> logs = workLogRepository.findAll().stream()
                .filter(wl -> wl.getProject().getUser().getId().equals(userId))
                .filter(wl -> wl.getStartTime() != null && !wl.getStartTime().isBefore(start) && wl.getStartTime().isBefore(end))
                .collect(Collectors.toList());
        long total = logs.stream().mapToLong(wl -> wl.getDurationMinutes() != null ? wl.getDurationMinutes() : 0).sum();
        ReportDTO dto = new ReportDTO();
        dto.setUserId(userId);
        dto.setStartDate(date);
        dto.setEndDate(date);
        dto.setTotalMinutes(total);
        dto.setDetails(logs.stream().map(wl -> wl.getDescription() + " (" + wl.getDurationMinutes() + " dk)").collect(Collectors.toList()));
        return dto;
    }

    public ReportDTO getWeeklyReport(Long userId, int weekOfYear) {
        LocalDate now = LocalDate.now();
        LocalDate start = now.with(java.time.temporal.IsoFields.WEEK_OF_WEEK_BASED_YEAR, weekOfYear).with(java.time.DayOfWeek.MONDAY);
        LocalDate end = start.plusDays(7);
        OffsetDateTime startDT = start.atStartOfDay().atOffset(OffsetDateTime.now().getOffset());
        OffsetDateTime endDT = end.atStartOfDay().atOffset(OffsetDateTime.now().getOffset());
        List<WorkLog> logs = workLogRepository.findAll().stream()
                .filter(wl -> wl.getProject().getUser().getId().equals(userId))
                .filter(wl -> wl.getStartTime() != null && !wl.getStartTime().isBefore(startDT) && wl.getStartTime().isBefore(endDT))
                .collect(Collectors.toList());
        long total = logs.stream().mapToLong(wl -> wl.getDurationMinutes() != null ? wl.getDurationMinutes() : 0).sum();
        ReportDTO dto = new ReportDTO();
        dto.setUserId(userId);
        dto.setStartDate(start);
        dto.setEndDate(end.minusDays(1));
        dto.setTotalMinutes(total);
        dto.setDetails(logs.stream().map(wl -> wl.getDescription() + " (" + wl.getDurationMinutes() + " dk)").collect(Collectors.toList()));
        return dto;
    }

    public byte[] exportReportToPDF(ReportDTO report) {
        // Stub: Gerçek PDF export burada olacak
        return new byte[0];
    }

    public byte[] exportReportToExcel(ReportDTO report) {
        // Stub: Gerçek Excel export burada olacak
        return new byte[0];
    }
} 