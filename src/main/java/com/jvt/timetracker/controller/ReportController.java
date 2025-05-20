package com.jvt.timetracker.controller;

import com.jvt.timetracker.dto.ReportDTO;
import com.jvt.timetracker.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @GetMapping("/daily")
    public ResponseEntity<ReportDTO> getDailyReport(@RequestParam Long userId, @RequestParam String date) {
        LocalDate localDate = LocalDate.parse(date);
        return ResponseEntity.ok(reportService.getDailyReport(userId, localDate));
    }

    @GetMapping("/weekly")
    public ResponseEntity<ReportDTO> getWeeklyReport(@RequestParam Long userId, @RequestParam int weekOfYear) {
        return ResponseEntity.ok(reportService.getWeeklyReport(userId, weekOfYear));
    }

    @PostMapping("/export/pdf")
    public ResponseEntity<byte[]> exportPDF(@RequestBody ReportDTO report) {
        return ResponseEntity.ok(reportService.exportReportToPDF(report));
    }

    @PostMapping("/export/excel")
    public ResponseEntity<byte[]> exportExcel(@RequestBody ReportDTO report) {
        return ResponseEntity.ok(reportService.exportReportToExcel(report));
    }
    
} 