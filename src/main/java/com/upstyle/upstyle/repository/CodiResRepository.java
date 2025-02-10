package com.upstyle.upstyle.repository;

import com.upstyle.upstyle.domain.CodiRequest;
import com.upstyle.upstyle.domain.CodiResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodiResRepository extends JpaRepository<CodiResponse, Long> {
}
