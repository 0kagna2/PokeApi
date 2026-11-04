package com.vedrunaExample2.PokeApi.controller.converter;
import com.vedrunaExample2.PokeApi.controller.dto.PokemonDto;
import org.springframework.stereotype.Component;
import com.vedrunaExample2.PokeApi.persistance.model.Pokemon;
@Component  
public class Converter {
    
    public PokemonDto convertToDto(Pokemon pokemon) {
        PokemonDto dto = new PokemonDto();
        dto.setName(pokemon.getName());
        dto.setId(pokemon.getId());
        dto.setType(pokemon.getType());
        dto.setDescription(pokemon.getDescription());
        return dto;
    }
    public Pokemon convertToEntity(PokemonDto dto) {
        Pokemon pokemon = new Pokemon();
        pokemon.setName(dto.getName());
        pokemon.setId(dto.getId());
        pokemon.setType(dto.getType());
        pokemon.setDescription(dto.getDescription());
        return pokemon;
    }

}
