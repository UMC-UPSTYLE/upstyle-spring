package com.upstyle.upstyle.domain;

import com.upstyle.upstyle.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Option extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "vote_id", nullable = false)
    private Vote vote;
}
