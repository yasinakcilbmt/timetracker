package com.jvt.timetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.jvt.timetracker.model.WorkLog;

public interface WorkLogRepository extends JpaRepository<WorkLog, Long>,  JpaSpecificationExecutor<WorkLog> {
}
