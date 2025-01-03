package com.upstyle.upstyle.domain;

import com.upstyle.upstyle.domain.common.BaseEntity;
import com.upstyle.upstyle.domain.mapping.OotdCloth;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cloth extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private ClothCategory category;

    @ManyToOne
    @JoinColumn(name = "fit_id", nullable = false)
    private ClothFit fit;

    @ManyToOne
    @JoinColumn(name = "color_id", nullable = false)
    private ClothColor color;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "cloth", cascade = CascadeType.ALL)
    private List<OotdCloth> ootdClothList = new ArrayList<>();
}
