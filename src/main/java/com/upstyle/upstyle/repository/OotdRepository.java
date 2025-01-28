package com.upstyle.upstyle.repository;

import com.upstyle.upstyle.domain.Ootd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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
}
