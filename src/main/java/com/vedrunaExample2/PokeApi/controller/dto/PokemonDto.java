package com.vedrunaExample2.PokeApi.controller.dto;

import lombok.Data;

@Data 
public class PokemonDto {
    private String name;
    private long id; 
    private String type;
    private String description;
}
