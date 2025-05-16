package com.jvt.timetracker.service;

import org.springframework.stereotype.Service;

import com.jvt.timetracker.repository.*;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class WorkLogService {
    private final WorkLogRepository workLogRepository;
    private final ProjectRepository projectRepository;

    

}
