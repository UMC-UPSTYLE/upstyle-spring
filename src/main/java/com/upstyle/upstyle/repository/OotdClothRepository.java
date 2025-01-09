package com.upstyle.upstyle.repository;

import com.upstyle.upstyle.domain.ClothFit;
import com.upstyle.upstyle.domain.mapping.OotdCloth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OotdClothRepository extends JpaRepository<OotdCloth, Long> {
}
