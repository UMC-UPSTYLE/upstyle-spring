package com.upstyle.upstyle.repository;

import com.upstyle.upstyle.domain.OotdImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OotdImageRepository extends JpaRepository<OotdImage, Long> {
}
