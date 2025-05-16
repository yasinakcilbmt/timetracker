package com.jvt.timetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jvt.timetracker.model.User;

public interface UserRepository extends JpaRepository<User, Long>    {

}
