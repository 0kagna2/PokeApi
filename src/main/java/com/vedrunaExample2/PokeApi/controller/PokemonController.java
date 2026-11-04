package com.vedrunaExample2.PokeApi.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import java.util.List;
import com.vedrunaExample2.PokeApi.persistance.model.Pokemon;
import com.vedrunaExample2.PokeApi.service.PokemonService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
@RestController 
@RequestMapping ("/api/v1/pokemons")
@CrossOrigin 
@AllArgsConstructor 
public class PokemonController {
    PokemonService pokemonService;
    @GetMapping 
    public ResponseEntity<List<Pokemon>> getAllPokemons() {
        List<Pokemon> pokemons = pokemonService.getAllPokemons();
        return ResponseEntity.ok(pokemons);
    }
    @GetMapping ("/{id}")
    public ResponseEntity<Pokemon> getPokemonById(@PathVariable long id) {
        Pokemon pokemon = pokemonService.getPokemonById(id);
        return ResponseEntity.ok(pokemon);
    }
    @PostMapping 
    public ResponseEntity<Pokemon> createPokemon(@RequestBody Pokemon pokemon) {
        Pokemon createdPokemon = pokemonService.createPokemon(pokemon);
        return ResponseEntity.ok(createdPokemon);
    }
    @PutMapping ("/{id}")
    public ResponseEntity<Pokemon> updatePokemon(@PathVariable long id, @RequestBody Pokemon pokemon) {
        Pokemon updatedPokemon = pokemonService.updatePokemon(id, pokemon);
        return ResponseEntity.ok(updatedPokemon);
    }
    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> deletePokemon(@PathVariable long id) {
        pokemonService.deletePokemon(id);
        return ResponseEntity.noContent().build();
    }
}
