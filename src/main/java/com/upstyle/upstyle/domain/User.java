package com.upstyle.upstyle.domain;

import com.upstyle.upstyle.domain.common.BaseEntity;
import com.upstyle.upstyle.domain.enums.Role;
import com.upstyle.upstyle.domain.mapping.ClothBookmark;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
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

    @Column
    private String password;

    @Column(nullable = false, unique = true)
    @Email
    private String email;

    @Column
    private String picture;
    @Column
    private Float height;
    @Column
    private Float weight;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10)")
    private Role role;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10)")
    private com.upstyle.upstyle.domain.enums.Gender gender;

    @Column(nullable = true, length = 255)
    private String imageUrl;

    @Builder.Default
    private Integer reward = 0;

    @Column(columnDefinition = "VARCHAR(15) DEFAULT 'ACTIVE'")
    private String status;

    private LocalDate inactiveDate;

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Ootd> ootdList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ClothBookmark> clothBookmarkList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Vote> voteList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<CodiRequest> codiRequestList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<CodiResponse> codiResponseList = new ArrayList<>();

    public void encodePassword(String password) {
        this.password = password;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }
}
