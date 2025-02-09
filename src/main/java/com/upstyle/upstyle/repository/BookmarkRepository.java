package com.upstyle.upstyle.repository;

import com.upstyle.upstyle.domain.Cloth;
import com.upstyle.upstyle.domain.Ootd;
import com.upstyle.upstyle.domain.mapping.ClothBookmark;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<ClothBookmark, Long> {
    @Query("SELECT b FROM ClothBookmark b " +
            "WHERE b.user.id = :userId " +
            "AND (:kindId IS NULL OR b.cloth.kind.id = :kindId) " +
            "ORDER BY b.createdAt DESC")
    Page<ClothBookmark> findBookmarkedClothes(@Param("userId") Long userId, @Param("kindId") Long kindId, Pageable pageable);
}
