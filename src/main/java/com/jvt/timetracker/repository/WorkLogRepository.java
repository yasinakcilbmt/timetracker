package com.jvt.timetracker.repository;

import com.jvt.timetracker.model.WorkLog;
import com.jvt.timetracker.model.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WorkLogRepository extends JpaRepository<WorkLog, Long> {
    List<WorkLog> findByProjectAndDeletedFalse(Project project);
    Page<WorkLog> findByProjectAndDeletedFalse(Project project, Pageable pageable);
    List<WorkLog> findByDeletedFalse();
    Page<WorkLog> findByDeletedFalse(Pageable pageable);
}
