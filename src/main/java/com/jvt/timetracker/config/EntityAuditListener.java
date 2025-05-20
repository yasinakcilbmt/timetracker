package com.jvt.timetracker.config;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.time.OffsetDateTime;

public class EntityAuditListener {
    
    @PrePersist
    public void prePersist(Object entity) {
        if (entity instanceof Auditable) {
            Auditable auditable = (Auditable) entity;
            OffsetDateTime now = OffsetDateTime.now();
            auditable.setCreatedAt(now);
            auditable.setUpdatedAt(now);
        }
    }
    
    @PreUpdate
    public void preUpdate(Object entity) {
        if (entity instanceof Auditable) {
            Auditable auditable = (Auditable) entity;
            auditable.setUpdatedAt(OffsetDateTime.now());
        }
    }
} 