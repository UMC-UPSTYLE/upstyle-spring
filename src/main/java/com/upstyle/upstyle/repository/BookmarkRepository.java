package com.upstyle.upstyle.repository;

import com.upstyle.upstyle.domain.Ootd;
import com.upstyle.upstyle.domain.mapping.ClothBookmark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<ClothBookmark, Long> {
}
