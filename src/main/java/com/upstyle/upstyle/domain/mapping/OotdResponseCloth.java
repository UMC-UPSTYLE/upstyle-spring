package com.upstyle.upstyle.domain.mapping;

import com.upstyle.upstyle.domain.Cloth;
import com.upstyle.upstyle.domain.OotdResponse;
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
public class OotdResponseCloth extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cloth_id", nullable = false)
    private Cloth cloth;

    @ManyToOne
    @JoinColumn(name = "response_id", nullable = false)
    private OotdResponse response;
}
