package com.vedrunaExample2.PokeApi.persistance.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import java.util.Set;

@Entity @Table(name = "abilities") @Data @NoArgsConstructor @EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Ability {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @EqualsAndHashCode.Include private Long id;
    @Column(nullable = false) private String name;
    private String description;
    @ManyToMany(mappedBy = "abilities") @JsonIgnore private Set<Pokemon> pokemons;
}
