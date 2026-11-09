package com.vedrunaExample2.PokeApi.persistance.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

@Entity 
@Table (name = "pokemons")
@Data 
@NoArgsConstructor 
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pokemon {
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "pokemon_id")
    @EqualsAndHashCode.Include
    long id;

    @Column (name = "pokemon_name")
    String name;

    @Column (name = "pokemon_description")
    String description;

    @Column (name = "pokemon_type")
    String type;

    @Column(name = "imagen_url")
    String imagenUrl;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    @JsonIgnore
    Trainer trainer;

    @JsonProperty("trainerId")
    public Long getTrainerId() { return trainer == null ? null : trainer.getId(); }

    @OneToOne(mappedBy = "pokemon")
    @JsonIgnore
    PokemonStats stats;

    @ManyToMany
    @JoinTable(name = "pokemon_abilities",
        joinColumns = @JoinColumn(name = "pokemon_id"),
        inverseJoinColumns = @JoinColumn(name = "ability_id"))
    @JsonIgnore
    Set<Ability> abilities;


}

