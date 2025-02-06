package com.upstyle.upstyle.repository;

import com.upstyle.upstyle.domain.VoteOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VoteOptionRepository extends JpaRepository<VoteOption, Long> {

    // 특정 투표 ID에 해당하는 모든 옵션 조회
    @Query("SELECT vo FROM VoteOption vo WHERE vo.vote.id = :voteId")
    List<VoteOption> findByVoteId(@Param("voteId") Long voteId);
}
