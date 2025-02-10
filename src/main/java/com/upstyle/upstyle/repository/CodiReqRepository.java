package com.upstyle.upstyle.repository;

import com.upstyle.upstyle.domain.CodiRequest;
import com.upstyle.upstyle.domain.Vote;
import com.upstyle.upstyle.domain.mapping.OotdCloth;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CodiReqRepository extends JpaRepository<CodiRequest, Long> {
    // 최신 순으로 정렬된 투표 목록을 페이지 단위로 조회 (옵션 목록도 함께 페치 조인)
    @Query(value = "SELECT c FROM CodiRequest c ORDER BY c.createdAt DESC",
            countQuery = "SELECT COUNT(c) FROM Vote c")  // 페이징을 위해 총 카운트 쿼리 필요
    Page<CodiRequest> findPagedCodiReq(Pageable pageable);
}
