package com.jvt.timetracker.controller;

import com.jvt.timetracker.dto.ProjectRequestDTO;
import com.jvt.timetracker.dto.ProjectResponseDTO;
import com.jvt.timetracker.mapper.ModelMapper;
import com.jvt.timetracker.model.Project;
import com.jvt.timetracker.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ProjectResponseDTO> createProject(@RequestBody ProjectRequestDTO projectDTO) {
        Project project = modelMapper.toProject(projectDTO);
        Project createdProject = projectService.createProject(project);
        return ResponseEntity.ok(modelMapper.toProjectResponseDTO(createdProject));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponseDTO> getProjectById(@PathVariable Long id) {
        Project project = projectService.getProjectById(id);
        return ResponseEntity.ok(modelMapper.toProjectResponseDTO(project));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Page<ProjectResponseDTO>> getProjectsByUserId(
            @PathVariable Long userId,
            Pageable pageable) {
        Page<Project> projects = projectService.getProjectsByUserId(userId, pageable);
        Page<ProjectResponseDTO> projectDTOs = projects.map(modelMapper::toProjectResponseDTO);
        return ResponseEntity.ok(projectDTOs);
    }

    @GetMapping("/user/{userId}/all")
    public ResponseEntity<List<ProjectResponseDTO>> getAllProjectsByUserId(@PathVariable Long userId) {
        List<Project> projects = projectService.getProjectsByUserId(userId);
        List<ProjectResponseDTO> projectDTOs = projects.stream()
                .map(modelMapper::toProjectResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(projectDTOs);
    }

    @GetMapping
    public ResponseEntity<Page<ProjectResponseDTO>> getAllProjects(Pageable pageable) {
        Page<Project> projects = projectService.getAllProjects(pageable);
        Page<ProjectResponseDTO> projectDTOs = projects.map(modelMapper::toProjectResponseDTO);
        return ResponseEntity.ok(projectDTOs);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProjectResponseDTO>> getAllProjectsList() {
        List<Project> projects = projectService.getAllProjects();
        List<ProjectResponseDTO> projectDTOs = projects.stream()
                .map(modelMapper::toProjectResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(projectDTOs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponseDTO> updateProject(
            @PathVariable Long id,
            @RequestBody ProjectRequestDTO projectDTO) {
        Project project = modelMapper.toProject(projectDTO);
        Project updatedProject = projectService.updateProject(id, project);
        return ResponseEntity.ok(modelMapper.toProjectResponseDTO(updatedProject));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.ok().build();
    }
} 