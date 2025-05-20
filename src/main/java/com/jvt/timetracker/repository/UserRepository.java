package com.jvt.timetracker.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.jvt.timetracker.model.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>    {
    Optional<User> findByEmailAndDeletedFalse(String email);
    List<User> findByDeletedFalse();
    Page<User> findByDeletedFalse(Pageable pageable);
}
