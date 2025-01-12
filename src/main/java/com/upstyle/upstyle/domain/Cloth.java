package com.upstyle.upstyle.domain;

import com.upstyle.upstyle.domain.common.BaseEntity;
import com.upstyle.upstyle.domain.mapping.OotdCloth;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cloth extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private ClothCategory category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kind_id", nullable = false)
    private ClothKind kind;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fit_id", nullable = false)
    private ClothFit fit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "color_id", nullable = false)
    private ClothColor color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "cloth", cascade = CascadeType.ALL)
    private List<OotdCloth> ootdClothList = new ArrayList<>();
}
