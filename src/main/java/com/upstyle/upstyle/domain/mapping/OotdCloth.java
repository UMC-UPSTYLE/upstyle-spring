package com.upstyle.upstyle.domain.mapping;

import com.upstyle.upstyle.domain.Cloth;
import com.upstyle.upstyle.domain.Ootd;
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
public class OotdCloth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ootd_id", nullable = false)
    private Ootd ootd;

    @ManyToOne
    @JoinColumn(name = "cloth_id", nullable = false)
    private Cloth cloth;
}
