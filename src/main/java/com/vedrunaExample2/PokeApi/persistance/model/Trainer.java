package com.vedrunaExample2.PokeApi.persistance.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import java.util.List;

@Entity @Table(name = "trainers") @Data @NoArgsConstructor @EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Trainer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @EqualsAndHashCode.Include private Long id;
    @Column(nullable = false) private String name;
    private String hometown;
    @OneToMany(mappedBy = "trainer") @JsonIgnore private List<Pokemon> pokemons;
}
