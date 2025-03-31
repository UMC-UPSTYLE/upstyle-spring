package com.upstyle.upstyle.repository;

import com.upstyle.upstyle.domain.Uuid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UuidRepository extends JpaRepository<Uuid, Long> {
}
