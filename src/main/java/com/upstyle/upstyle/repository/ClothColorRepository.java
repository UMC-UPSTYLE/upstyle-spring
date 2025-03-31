package com.upstyle.upstyle.repository;

import com.upstyle.upstyle.domain.ClothColor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClothColorRepository extends JpaRepository<ClothColor, Long> {
}
