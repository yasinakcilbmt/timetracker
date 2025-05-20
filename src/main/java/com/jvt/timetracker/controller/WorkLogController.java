package com.jvt.timetracker.controller;

import com.jvt.timetracker.dto.WorkLogRequestDTO;
import com.jvt.timetracker.dto.WorkLogResponseDTO;
import com.jvt.timetracker.mapper.ModelMapper;
import com.jvt.timetracker.model.WorkLog;
import com.jvt.timetracker.service.WorkLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/worklogs")
@RequiredArgsConstructor
public class WorkLogController {
    private final WorkLogService workLogService;
    private final ModelMapper modelMapper;

    @PostMapping("/project/{projectId}/clock-in")
    public ResponseEntity<WorkLog> clockIn(@PathVariable Long projectId) {
        return ResponseEntity.ok(workLogService.clockIn(projectId));
    }

    @PostMapping("/{id}/clock-out")
    public ResponseEntity<WorkLog> clockOut(@PathVariable Long id) {
        return ResponseEntity.ok(workLogService.clockOut(id));
    }

    @PostMapping
    public ResponseEntity<WorkLogResponseDTO> createWorkLog(@RequestBody WorkLogRequestDTO workLogDTO) {
        WorkLog workLog = modelMapper.toWorkLog(workLogDTO);
        WorkLog createdWorkLog = workLogService.createWorkLog(workLog);
        return ResponseEntity.ok(modelMapper.toWorkLogResponseDTO(createdWorkLog));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkLogResponseDTO> getWorkLogById(@PathVariable Long id) {
        WorkLog workLog = workLogService.getWorkLogById(id);
        return ResponseEntity.ok(modelMapper.toWorkLogResponseDTO(workLog));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<Page<WorkLogResponseDTO>> getWorkLogsByProjectId(
            @PathVariable Long projectId,
            Pageable pageable) {
        Page<WorkLog> workLogs = workLogService.getWorkLogsByProjectId(projectId, pageable);
        Page<WorkLogResponseDTO> workLogDTOs = workLogs.map(modelMapper::toWorkLogResponseDTO);
        return ResponseEntity.ok(workLogDTOs);
    }

    @GetMapping("/project/{projectId}/all")
    public ResponseEntity<List<WorkLogResponseDTO>> getAllWorkLogsByProjectId(@PathVariable Long projectId) {
        List<WorkLog> workLogs = workLogService.getWorkLogsByProjectId(projectId);
        List<WorkLogResponseDTO> workLogDTOs = workLogs.stream()
                .map(modelMapper::toWorkLogResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(workLogDTOs);
    }

    @GetMapping
    public ResponseEntity<Page<WorkLogResponseDTO>> getAllWorkLogs(Pageable pageable) {
        Page<WorkLog> workLogs = workLogService.getAllWorkLogs(pageable);
        Page<WorkLogResponseDTO> workLogDTOs = workLogs.map(modelMapper::toWorkLogResponseDTO);
        return ResponseEntity.ok(workLogDTOs);
    }

    @GetMapping("/all")
    public ResponseEntity<List<WorkLogResponseDTO>> getAllWorkLogsList() {
        List<WorkLog> workLogs = workLogService.getAllWorkLogs();
        List<WorkLogResponseDTO> workLogDTOs = workLogs.stream()
                .map(modelMapper::toWorkLogResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(workLogDTOs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkLogResponseDTO> updateWorkLog(
            @PathVariable Long id,
            @RequestBody WorkLogRequestDTO workLogDTO) {
        WorkLog workLog = modelMapper.toWorkLog(workLogDTO);
        WorkLog updatedWorkLog = workLogService.updateWorkLog(id, workLog);
        return ResponseEntity.ok(modelMapper.toWorkLogResponseDTO(updatedWorkLog));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkLog(@PathVariable Long id) {
        workLogService.deleteWorkLog(id);
        return ResponseEntity.ok().build();
    }
} 