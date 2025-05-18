package com.jvt.timetracker.service;

import java.time.OffsetDateTime;

import org.springframework.stereotype.Service;

import com.jvt.timetracker.model.Project;
import com.jvt.timetracker.model.WorkLog;
import com.jvt.timetracker.repository.*;
import com.jvt.timetracker.exception.NotFoundException;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class WorkLogService {
    private final WorkLogRepository workLogRepository;
    private final ProjectRepository projectRepository;

    /**
     * Belirtilen projede yeni bir çalışma kaydı (giriş) başlatır.
     * @param projectId Çalışma kaydının başlatılacağı projenin ID'si
     * @return Kaydedilen WorkLog nesnesi
     */
    public WorkLog clockIn(Long projectId){

        Project project = projectRepository.findById(projectId)
        .orElseThrow(() -> new RuntimeException("Project not found"));

        WorkLog workLog = new WorkLog();
        workLog.setProject(project);
        workLog.setStartTime(OffsetDateTime.now());
        return workLogRepository.save(workLog);
    }

    /**
     * Belirtilen WorkLog kaydını bitirir (çıkış yapar) ve süreyi hesaplar.
     * @param workLogId Çıkış yapılacak WorkLog kaydının ID'si
     * @return Güncellenmiş WorkLog nesnesi
     * @throws NotFoundException Eğer WorkLog bulunamazsa
     * @throws IllegalStateException Eğer zaten çıkış yapılmışsa
     */
    public WorkLog clockOut(Long workLogId) {
        WorkLog log = workLogRepository.findById(workLogId).orElseThrow(() -> new NotFoundException("WorkLog not found"));

        if (log.getEndTime() != null) {
            throw new IllegalStateException("Already clocked out");
        }

        log.setEndTime(OffsetDateTime.now());
        log.setDurationMinutes(java.time.Duration.between(log.getStartTime(), log.getEndTime()).toMinutes());
        return workLogRepository.save(log);
    }
    
    

    

}
