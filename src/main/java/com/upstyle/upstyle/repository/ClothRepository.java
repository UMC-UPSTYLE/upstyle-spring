package com.upstyle.upstyle.repository;

import com.upstyle.upstyle.domain.Cloth;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface ClothRepository extends JpaRepository<Cloth, Long> {

    // 특정 사용자의 옷 종류별로 가장 최근 등록된 옷 조회
    // 특정 사용자의 옷 종류별로 가장 최근 등록된 옷 조회
    @Query("SELECT c.kind.id AS kindId, MAX(c.createdAt) AS latestDate, c.id AS clothId, c.imageUrl AS imageUrl " +
            "FROM Cloth c " +
            "WHERE c.user.id = :userId " +
            "GROUP BY c.kind.id")
    List<Object[]> findLatestClothByKindAndUserId(@Param("userId") Long userId);
}
