package com.jvt.timetracker.repository;

import com.jvt.timetracker.model.Project;
import com.jvt.timetracker.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByUserAndDeletedFalse(User user);
    Page<Project> findByUserAndDeletedFalse(User user, Pageable pageable);
    List<Project> findByDeletedFalse();
    Page<Project> findByDeletedFalse(Pageable pageable);
}
