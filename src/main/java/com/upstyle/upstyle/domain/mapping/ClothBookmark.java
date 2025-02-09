package com.upstyle.upstyle.domain.mapping;

import com.upstyle.upstyle.domain.Cloth;
import com.upstyle.upstyle.domain.User;
import com.upstyle.upstyle.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClothBookmark extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cloth_id", nullable = false)
    private Cloth cloth;

    // 유저가 북마크했을 때의 OOTD 정보 저장
    private Long ootdId;
    private String ootdImageUrl;
}
