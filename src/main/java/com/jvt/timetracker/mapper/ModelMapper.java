package com.jvt.timetracker.mapper;

import com.jvt.timetracker.dto.*;
import com.jvt.timetracker.model.User;
import com.jvt.timetracker.model.Project;
import com.jvt.timetracker.model.WorkLog;
import com.jvt.timetracker.service.UserService;
import com.jvt.timetracker.service.ProjectService;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ModelMapper {
    private final UserService userService;
    private final ProjectService projectService;

    // User Mappers
    public User toUser(UserRequestDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return user;
    }

    public UserResponseDTO toUserResponseDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setUpdatedAt(user.getUpdatedAt());
        return dto;
    }

    // Project Mappers
    public Project toProject(ProjectRequestDTO dto) {
        Project project = new Project();
        project.setName(dto.getName());
        project.setDescription(dto.getDescription());
        if (dto.getUserId() != null) {
            User user = userService.getUserById(dto.getUserId());
            project.setUser(user);
        }
        return project;
    }

    public ProjectResponseDTO toProjectResponseDTO(Project project) {
        ProjectResponseDTO dto = new ProjectResponseDTO();
        dto.setId(project.getId());
        dto.setName(project.getName());
        dto.setDescription(project.getDescription());
        dto.setUserId(project.getUser().getId());
        dto.setUserName(project.getUser().getName());
        dto.setCreatedAt(project.getCreatedAt());
        dto.setUpdatedAt(project.getUpdatedAt());
        return dto;
    }

    // WorkLog Mappers
    public WorkLog toWorkLog(WorkLogRequestDTO dto) {
        WorkLog workLog = new WorkLog();
        if (dto.getProjectId() != null) {
            Project project = projectService.getProjectById(dto.getProjectId());
            workLog.setProject(project);
        }
        workLog.setStartTime(dto.getStartTime());
        workLog.setEndTime(dto.getEndTime());
        workLog.setDescription(dto.getDescription());
        // durationMinutes hesapla veya 0 olarak başlat
        if (dto.getStartTime() != null && dto.getEndTime() != null) {
            workLog.setDurationMinutes(java.time.Duration.between(dto.getStartTime(), dto.getEndTime()).toMinutes());
        } else {
            workLog.setDurationMinutes(0L);
        }
        workLog.setDeleted(false);
        workLog.setCreatedAt(java.time.OffsetDateTime.now());
        workLog.setUpdatedAt(java.time.OffsetDateTime.now());
        return workLog;
    }

    public WorkLogResponseDTO toWorkLogResponseDTO(WorkLog workLog) {
        WorkLogResponseDTO dto = new WorkLogResponseDTO();
        dto.setId(workLog.getId());
        dto.setProjectId(workLog.getProject().getId());
        dto.setProjectName(workLog.getProject().getName());
        dto.setUserId(workLog.getProject().getUser().getId());
        dto.setUserName(workLog.getProject().getUser().getName());
        dto.setStartTime(workLog.getStartTime());
        dto.setEndTime(workLog.getEndTime());
        dto.setDurationMinutes(workLog.getDurationMinutes());
        dto.setDescription(workLog.getDescription());
        dto.setCreatedAt(workLog.getCreatedAt());
        dto.setUpdatedAt(workLog.getUpdatedAt());
        return dto;
    }
} 