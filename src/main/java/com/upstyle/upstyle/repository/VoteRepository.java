package com.upstyle.upstyle.repository;

import com.upstyle.upstyle.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    @Query("SELECT v FROM Vote v LEFT JOIN FETCH v.optionList WHERE v.id = :id")
    Optional<Vote> findVoteWithOptions(@Param("id") Long id);
}
