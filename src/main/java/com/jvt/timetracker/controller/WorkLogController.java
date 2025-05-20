package com.jvt.timetracker.controller;

import com.jvt.timetracker.model.WorkLog;
import com.jvt.timetracker.service.WorkLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/worklogs")
@RequiredArgsConstructor
public class WorkLogController {
    private final WorkLogService workLogService;

    @PostMapping("/project/{projectId}/clock-in")
    public ResponseEntity<WorkLog> clockIn(@PathVariable Long projectId) {
        return ResponseEntity.ok(workLogService.clockIn(projectId));
    }

    @PostMapping("/{id}/clock-out")
    public ResponseEntity<WorkLog> clockOut(@PathVariable Long id) {
        return ResponseEntity.ok(workLogService.clockOut(id));
    }

    @PostMapping
    public ResponseEntity<WorkLog> createWorkLog(@RequestBody WorkLog workLog) {
        return ResponseEntity.ok(workLogService.createWorkLog(workLog));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkLog> getWorkLogById(@PathVariable Long id) {
        return ResponseEntity.ok(workLogService.getWorkLogById(id));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<Page<WorkLog>> getWorkLogsByProjectId(
            @PathVariable Long projectId,
            Pageable pageable) {
        return ResponseEntity.ok(workLogService.getWorkLogsByProjectId(projectId, pageable));
    }

    @GetMapping("/project/{projectId}/all")
    public ResponseEntity<List<WorkLog>> getAllWorkLogsByProjectId(@PathVariable Long projectId) {
        return ResponseEntity.ok(workLogService.getWorkLogsByProjectId(projectId));
    }

    @GetMapping
    public ResponseEntity<Page<WorkLog>> getAllWorkLogs(Pageable pageable) {
        return ResponseEntity.ok(workLogService.getAllWorkLogs(pageable));
    }

    @GetMapping("/all")
    public ResponseEntity<List<WorkLog>> getAllWorkLogsList() {
        return ResponseEntity.ok(workLogService.getAllWorkLogs());
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkLog> updateWorkLog(
            @PathVariable Long id,
            @RequestBody WorkLog workLogDetails) {
        return ResponseEntity.ok(workLogService.updateWorkLog(id, workLogDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkLog(@PathVariable Long id) {
        workLogService.deleteWorkLog(id);
        return ResponseEntity.ok().build();
    }
} 