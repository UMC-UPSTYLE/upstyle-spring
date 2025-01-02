package com.upstyle.upstyle.domain;

import com.upstyle.upstyle.domain.common.BaseEntity;
import com.upstyle.upstyle.domain.mapping.OotdBookmark;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nickname;

    @Column(nullable = false)
    private String password;

    private Float height;
    private Float weight;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10)")
    private com.upstyle.upstyle.domain.enums.Gender gender;

    @Column(nullable = true, length = 255)
    private String imageUrl;

    private Integer reward;

    @Column(length = 15)
    private String status;

    private LocalDate inactiveDate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Ootd> ootds;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<OotdBookmark> ootdBookmarks;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Vote> votes;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<OotdRequest> ootdRequests;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<OotdResponse> ootdResponses;
}
