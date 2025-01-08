package com.upstyle.upstyle.repository;

import com.upstyle.upstyle.domain.Cloth;
import org.springframework.stereotype.Repository;

@Repository
public interface ClothRepository extends JpaRepository<Cloth, Long> {
}
