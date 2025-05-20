package com.jvt.timetracker.service;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
    private final ProjectService projectService;

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

    /**
     * Yeni bir iş kaydı oluşturur ve veritabanına kaydeder.
     * @param workLog Oluşturulacak iş kaydı nesnesi
     * @return Kaydedilen iş kaydı
     */
    public WorkLog createWorkLog(WorkLog workLog) {
        return workLogRepository.save(workLog);
    }

    /**
     * Belirtilen ID'ye sahip iş kaydını getirir.
     * @param id İş kaydı ID'si
     * @return İş kaydı nesnesi
     * @throws RuntimeException Eğer iş kaydı bulunamazsa
     */
    public WorkLog getWorkLogById(Long id) {
        return workLogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("WorkLog not found"));
    }

    /**
     * Belirli bir projenin iş kayıtlarını sayfalı olarak listeler.
     * @param projectId Proje ID'si
     * @param pageable Sayfalama bilgileri
     * @return Sayfalanmış iş kaydı listesi
     */
    public Page<WorkLog> getWorkLogsByProjectId(Long projectId, Pageable pageable) {
        Project project = projectService.getProjectById(projectId);
        return workLogRepository.findByProjectAndDeletedFalse(project, pageable);
    }

    /**
     * Belirli bir projenin tüm iş kayıtlarını listeler.
     * @param projectId Proje ID'si
     * @return İş kaydı listesi
     */
    public List<WorkLog> getWorkLogsByProjectId(Long projectId) {
        Project project = projectService.getProjectById(projectId);
        return workLogRepository.findByProjectAndDeletedFalse(project);
    }

    /**
     * Tüm iş kayıtlarını sayfalı olarak listeler.
     * @param pageable Sayfalama bilgileri
     * @return Sayfalanmış iş kaydı listesi
     */
    public Page<WorkLog> getAllWorkLogs(Pageable pageable) {
        return workLogRepository.findByDeletedFalse(pageable);
    }

    /**
     * Tüm iş kayıtlarını listeler.
     * @return İş kaydı listesi
     */
    public List<WorkLog> getAllWorkLogs() {
        return workLogRepository.findByDeletedFalse();
    }

    /**
     * Var olan bir iş kaydını günceller.
     * @param id Güncellenecek iş kaydının ID'si
     * @param workLogDetails Yeni iş kaydı bilgileri
     * @return Güncellenmiş iş kaydı
     */
    public WorkLog updateWorkLog(Long id, WorkLog workLogDetails) {
        WorkLog workLog = getWorkLogById(id);
        workLog.setStartTime(workLogDetails.getStartTime());
        workLog.setEndTime(workLogDetails.getEndTime());
        workLog.setDurationMinutes(workLogDetails.getDurationMinutes());
        return workLogRepository.save(workLog);
    }

    /**
     * Belirtilen ID'ye sahip iş kaydını soft delete yapar.
     * @param id Silinecek iş kaydının ID'si
     */
    public void deleteWorkLog(Long id) {
        WorkLog workLog = getWorkLogById(id);
        workLog.setDeleted(true);
        workLogRepository.save(workLog);
    }
}
