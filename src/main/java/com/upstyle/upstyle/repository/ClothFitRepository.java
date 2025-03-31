package com.upstyle.upstyle.repository;

import com.upstyle.upstyle.domain.ClothFit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClothFitRepository extends JpaRepository<ClothFit, Long> {
}
