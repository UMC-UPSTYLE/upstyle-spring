package com.upstyle.upstyle.repository;

import com.upstyle.upstyle.domain.ClothCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClothCategoryRepository extends JpaRepository<ClothCategory, Long> {
}
