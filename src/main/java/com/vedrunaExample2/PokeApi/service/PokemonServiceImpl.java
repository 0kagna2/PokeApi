package com.vedrunaExample2.PokeApi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vedrunaExample2.PokeApi.persistance.model.Pokemon;
import com.vedrunaExample2.PokeApi.persistance.repository.PokemonRepository;
import lombok.AllArgsConstructor;

@Service 
@AllArgsConstructor 
public class PokemonServiceImpl implements PokemonService {
    PokemonRepository pokemonRepository;
    @Override 
    public List<Pokemon> getAllPokemons() {
        return pokemonRepository.findAll();
    }
    @Override 
    public Pokemon getPokemonById(long id) {
        return pokemonRepository.findById(id).orElseThrow(() -> new RuntimeException("Pokemon not found"));
    }
    @Override 
    public Pokemon createPokemon(Pokemon pokemon) {
        return pokemonRepository.save(pokemon);
    }
    @Override
    public Pokemon updatePokemon(long id, Pokemon pokemon) {
       
       
        Pokemon existingPokemon = pokemonRepository.findById(id).orElseThrow(() -> new RuntimeException("Pokemon not found"));
        existingPokemon.setName(pokemon.getName());
        existingPokemon.setType(pokemon.getType());
        existingPokemon.setDescription(pokemon.getDescription());
        return pokemonRepository.save(existingPokemon);
    }
    @Override
    public void deletePokemon(long id) {
        if (!pokemonRepository.existsById(id)) {
            throw new RuntimeException("Pokemon not found");
        }
        pokemonRepository.deleteById(id);
    }
}
