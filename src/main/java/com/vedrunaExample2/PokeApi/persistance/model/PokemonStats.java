package com.vedrunaExample2.PokeApi.persistance.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;

@Entity @Table(name = "pokemon_stats") @Data @NoArgsConstructor @EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PokemonStats {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @EqualsAndHashCode.Include private Long id;
    @Column(nullable = false) private Integer hp;
    @Column(nullable = false) private Integer attack;
    @Column(nullable = false) private Integer defense;
    @Column(nullable = false) private Integer speed;
    private BigDecimal height;
    private BigDecimal weight;
    @OneToOne @JoinColumn(name = "pokemon_id", nullable = false, unique = true) @JsonIgnore private Pokemon pokemon;
}
