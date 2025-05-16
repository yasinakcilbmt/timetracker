package com.jvt.timetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jvt.timetracker.model.Project;
import com.jvt.timetracker.model.User;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByUser(User user);

}
