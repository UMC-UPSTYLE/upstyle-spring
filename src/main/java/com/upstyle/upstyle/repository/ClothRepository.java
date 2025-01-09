package com.upstyle.upstyle.repository;

import com.upstyle.upstyle.domain.Cloth;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ClothRepository extends JpaRepository<Cloth, Long> {
}
