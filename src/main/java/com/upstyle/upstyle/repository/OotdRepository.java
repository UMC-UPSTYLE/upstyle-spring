package com.upstyle.upstyle.repository;

import com.upstyle.upstyle.domain.Ootd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OotdRepository extends JpaRepository<Ootd, Long> {
}
