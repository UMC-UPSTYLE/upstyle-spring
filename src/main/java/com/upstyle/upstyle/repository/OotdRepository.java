package com.upstyle.upstyle.repository;

import com.upstyle.upstyle.domain.Ootd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OotdRepository extends JpaRepository<Ootd, Long> {
    @Query("SELECT o.id AS userId, o.date AS time, oi.imageUrl AS imageurl " +
            "FROM Ootd o " +
            "JOIN OotdImage oi ON o.id = oi.ootd.id " +
            "WHERE o.user.id = :userId " +
            "AND FUNCTION('YEAR', o.date) = :year " +
            "AND FUNCTION('MONTH', o.date) = :month ")
    List<Object[]> findAllByUserIdAndYearAndMonth(
            @Param("userId") Long userId,
            @Param("year") int year,
            @Param("month") int month
    );

    @Query("SELECT o FROM Ootd o " +
            "LEFT JOIN FETCH o.ootdClothList " +
            "WHERE o.id = :ootdId")
    Optional<Ootd> findWithClothList(@Param("ootdId") Long ootdId);

    @Query("SELECT o FROM Ootd o " +
            "LEFT JOIN FETCH o.ootdImageList " +
            "WHERE o.id = :ootdId")
    Optional<Ootd> findWithImageList(@Param("ootdId") Long ootdId);

    @Query("SELECT o FROM Ootd o JOIN o.ootdClothList oc " +
            "WHERE oc.cloth.id = :clothId " +
            "ORDER BY o.createdAt DESC LIMIT 1")
    Ootd findLatestOotdByClothId(@Param("clothId") Long clothId);
}
