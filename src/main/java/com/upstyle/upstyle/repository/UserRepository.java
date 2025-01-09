package com.upstyle.upstyle.repository;

import com.upstyle.upstyle.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
