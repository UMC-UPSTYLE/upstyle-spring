package com.upstyle.upstyle.repository;

import com.upstyle.upstyle.domain.Vote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    @Query("SELECT v FROM Vote v LEFT JOIN FETCH v.optionList WHERE v.id = :id")
    Optional<Vote> findVoteById(@Param("id") Long id);

    // 최신 순으로 정렬된 투표 목록을 페이지 단위로 조회 (옵션 목록도 함께 페치 조인)
    @Query(value = "SELECT v FROM Vote v LEFT JOIN FETCH v.optionList ORDER BY v.createdAt DESC",
            countQuery = "SELECT COUNT(v) FROM Vote v")  // 페이징을 위해 총 카운트 쿼리 필요
    Page<Vote> findPagedVotes(Pageable pageable);
}
