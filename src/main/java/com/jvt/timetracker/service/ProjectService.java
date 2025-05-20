package com.jvt.timetracker.service;

import com.jvt.timetracker.model.Project;
import com.jvt.timetracker.model.User;
import com.jvt.timetracker.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final UserService userService;

    /**
     * Yeni bir proje oluşturur ve veritabanına kaydeder.
     * @param project Oluşturulacak proje nesnesi
     * @return Kaydedilen proje
     */
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    /**
     * Belirtilen ID'ye sahip projeyi getirir.
     * @param id Proje ID'si
     * @return Proje nesnesi
     * @throws RuntimeException Eğer proje bulunamazsa
     */
    public Project getProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }

    /**
     * Belirli bir kullanıcının projelerini sayfalı olarak listeler.
     * @param userId Kullanıcı ID'si
     * @param pageable Sayfalama bilgileri
     * @return Sayfalanmış proje listesi
     */
    public Page<Project> getProjectsByUserId(Long userId, Pageable pageable) {
        User user = userService.getUserById(userId);
        return projectRepository.findByUserAndDeletedFalse(user, pageable);
    }

    /**
     * Belirli bir kullanıcının tüm projelerini listeler.
     * @param userId Kullanıcı ID'si
     * @return Proje listesi
     */
    public List<Project> getProjectsByUserId(Long userId) {
        User user = userService.getUserById(userId);
        return projectRepository.findByUserAndDeletedFalse(user);
    }

    /**
     * Tüm projeleri sayfalı olarak listeler.
     * @param pageable Sayfalama bilgileri
     * @return Sayfalanmış proje listesi
     */
    public Page<Project> getAllProjects(Pageable pageable) {
        return projectRepository.findByDeletedFalse(pageable);
    }

    /**
     * Tüm projeleri listeler.
     * @return Proje listesi
     */
    public List<Project> getAllProjects() {
        return projectRepository.findByDeletedFalse();
    }

    /**
     * Var olan bir projeyi günceller.
     * @param id Güncellenecek projenin ID'si
     * @param projectDetails Yeni proje bilgileri
     * @return Güncellenmiş proje
     */
    public Project updateProject(Long id, Project projectDetails) {
        Project project = getProjectById(id);
        project.setName(projectDetails.getName());
        return projectRepository.save(project);
    }

    /**
     * Belirtilen ID'ye sahip projeyi soft delete yapar.
     * @param id Silinecek projenin ID'si
     */
    public void deleteProject(Long id) {
        Project project = getProjectById(id);
        project.setDeleted(true);
        projectRepository.save(project);
    }
} 