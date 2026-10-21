package com.vedrunaExample2.PokeApi.persistance.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Table (name = "pokemons")
@Data 
@NoArgsConstructor 
public class Pokemon {
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "pokemon_id")
    long id;

    @Column (name = "pokemon_name")
    String name;

    @Column (name = "pokemon_description")
    String description;

    @Column (name = "pokemon_type")
    String type;


}

