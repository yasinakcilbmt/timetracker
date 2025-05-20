package com.jvt.timetracker.config;

import java.time.OffsetDateTime;

public interface Auditable {
    void setCreatedAt(OffsetDateTime createdAt);
    void setUpdatedAt(OffsetDateTime updatedAt);
} 