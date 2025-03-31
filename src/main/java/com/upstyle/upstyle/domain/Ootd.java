package com.upstyle.upstyle.domain;

import com.upstyle.upstyle.domain.common.BaseEntity;
import com.upstyle.upstyle.domain.mapping.OotdCloth;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ootd extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private LocalDate date;

    @OneToMany(mappedBy = "ootd", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @BatchSize(size = 10)
    private List<OotdCloth> ootdClothList = new ArrayList<>();

    @OneToMany(mappedBy = "ootd", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @BatchSize(size = 10)
    private List<OotdImage> ootdImageList = new ArrayList<>();
}
