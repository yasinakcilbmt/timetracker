package com.jvt.timetracker.service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jvt.timetracker.model.Project;
import com.jvt.timetracker.model.User;
import com.jvt.timetracker.model.WorkLog;
import com.jvt.timetracker.repository.*;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class WorkLogService {
    private final WorkLogRepository workLogRepository;
    private final ProjectRepository projectRepository;

    public WorkLog clockIn(Long projectId){

        Project project = projectRepository.findById(projectId)
        .orElseThrow(() -> new RuntimeException("Project not found"));

        WorkLog workLog = new WorkLog();
        workLog.setProject(project);
        workLog.setStartTime(OffsetDateTime.now());
        return workLogRepository.save(workLog);
    }


    
        


    

    

}
