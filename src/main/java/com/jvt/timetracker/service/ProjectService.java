package com.jvt.timetracker.service;

import com.jvt.timetracker.model.Project;
import com.jvt.timetracker.model.User;
import com.jvt.timetracker.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

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
    public Project getProject(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }

    /**
     * Tüm projeleri listeler.
     * @return Proje listesi
     */
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    /**
     * Belirli bir kullanıcıya ait projeleri listeler.
     * @param user Kullanıcı nesnesi
     * @return Proje listesi
     */
    public List<Project> getProjectsByUser(User user) {
        return projectRepository.findByUser(user);
    }

    /**
     * Var olan bir projeyi günceller.
     * @param id Güncellenecek projenin ID'si
     * @param projectDetails Yeni proje bilgileri
     * @return Güncellenmiş proje
     */
    public Project updateProject(Long id, Project projectDetails) {
        Project project = getProject(id);
        project.setName(projectDetails.getName());
        project.setUser(projectDetails.getUser());
        return projectRepository.save(project);
    }

    /**
     * Belirtilen ID'ye sahip projeyi siler.
     * @param id Silinecek projenin ID'si
     */
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
} 