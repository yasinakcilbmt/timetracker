package com.jvt.timetracker.model;

import com.jvt.timetracker.config.Auditable;
import com.jvt.timetracker.config.EntityAuditListener;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.OffsetDateTime;

@Entity
@Table(name = "users")
@EntityListeners(EntityAuditListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 120)
    private String name;
    
    @Column(nullable = false, unique = true, length = 120)
    private String email;
    
    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false)
    private boolean deleted = false;
    
    @Column(nullable = false, updatable = false)
    private OffsetDateTime createdAt;
    
    @Column(nullable = false)
    private OffsetDateTime updatedAt;

    @Override
    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}