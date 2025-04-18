package com.upstyle.upstyle.repository;

import com.upstyle.upstyle.domain.Cloth;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface ClothRepository extends JpaRepository<Cloth, Long> {

    // 특정 사용자의 옷 종류별로 가장 최근 등록된 옷 조회
    @Query("SELECT k.id AS kindId, k.name AS kindName, COALESCE(img.imageUrl, NULL) AS thumbnailUrl " +
            "FROM ClothKind k " +
            "LEFT JOIN Cloth c ON k.id = c.kind.id AND c.user.id = :userId " +
            "LEFT JOIN OotdCloth oc ON c.id = oc.cloth.id " +
            "LEFT JOIN Ootd o ON oc.ootd.id = o.id " +
            "LEFT JOIN OotdImage img ON o.id = img.ootd.id " +
            "AND o.date = (SELECT MAX(o2.date) FROM Ootd o2 JOIN OotdCloth oc2 ON o2.id = oc2.ootd.id WHERE oc2.cloth.id = c.id) " +
            "GROUP BY k.id, k.name, img.imageUrl")
    List<Object[]> findLatestClothByKindAndUserId(@Param("userId") Long userId);

    @Query("SELECT c FROM Cloth c " +
            "WHERE (:userId IS NULL OR c.user.id = :userId) " +
            "AND (:kindId IS NULL OR c.kind.id = :kindId) " +
            "AND (:categoryId IS NULL OR c.category.id = :categoryId) " +
            "AND (:fitId IS NULL OR c.fit.id = :fitId)" +
            "AND (:#{#colorIds == null or #colorIds.isEmpty()} = true OR c.color.id IN :colorIds)" +
            "ORDER BY c.createdAt DESC")
    Page<Cloth> findClothesByFilters(@Param("userId") Long userId,
                                     @Param("kindId") Long kindId,
                                     @Param("categoryId") Long categoryId,
                                     @Param("colorIds") List<Long> colorIds,
                                     @Param("fitId") Long fitId,
                                     Pageable pageable);


}
