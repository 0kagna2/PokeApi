package com.vedrunaExample2.PokeApi.controller;

import com.vedrunaExample2.PokeApi.persistance.model.*;
import com.vedrunaExample2.PokeApi.persistance.repository.*;
import com.vedrunaExample2.PokeApi.service.PokemonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/pokemons")
@CrossOrigin
@AllArgsConstructor
public class PokemonController {
    private final PokemonService pokemonService;
    private final PokemonRepository pokemonRepository;
    private final PokemonStatsRepository statsRepository;
    private final AbilityRepository abilityRepository;

    @GetMapping public ResponseEntity<List<Pokemon>> getAllPokemons() { return ResponseEntity.ok(pokemonService.getAllPokemons()); }
    @GetMapping("/{id}") public ResponseEntity<Pokemon> getPokemonById(@PathVariable long id) { return ResponseEntity.ok(pokemonService.getPokemonById(id)); }
    @PostMapping public ResponseEntity<Pokemon> createPokemon(@RequestBody Pokemon pokemon) { return ResponseEntity.status(HttpStatus.CREATED).body(pokemonService.createPokemon(pokemon)); }
    @PutMapping("/{id}") public ResponseEntity<Pokemon> updatePokemon(@PathVariable long id, @RequestBody Pokemon pokemon) { return ResponseEntity.ok(pokemonService.updatePokemon(id, pokemon)); }
    @DeleteMapping("/{id}") public ResponseEntity<Void> deletePokemon(@PathVariable long id) { pokemonService.deletePokemon(id); return ResponseEntity.noContent().build(); }

    @PostMapping("/{id}/stats")
    public ResponseEntity<PokemonStats> createStats(@PathVariable Long id, @RequestBody PokemonStats stats) {
        Pokemon pokemon = requirePokemon(id);
        if (statsRepository.findByPokemonId(id).isPresent()) throw new ResponseStatusException(HttpStatus.CONFLICT, "Pokemon already has stats");
        stats.setId(null); stats.setPokemon(pokemon);
        return ResponseEntity.status(HttpStatus.CREATED).body(statsRepository.save(stats));
    }
    @GetMapping("/{id}/stats")
    public ResponseEntity<PokemonStats> getStats(@PathVariable Long id) {
        requirePokemon(id);
        return ResponseEntity.ok(statsRepository.findByPokemonId(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Stats not found")));
    }
    @PutMapping("/{id}/stats")
    public ResponseEntity<PokemonStats> updateStats(@PathVariable Long id, @RequestBody PokemonStats request) {
        requirePokemon(id);
        PokemonStats stats = statsRepository.findByPokemonId(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Stats not found"));
        stats.setHp(request.getHp()); stats.setAttack(request.getAttack()); stats.setDefense(request.getDefense()); stats.setSpeed(request.getSpeed()); stats.setHeight(request.getHeight()); stats.setWeight(request.getWeight());
        return ResponseEntity.ok(statsRepository.save(stats));
    }

    @PostMapping("/{id}/abilities/{abilityId}")
    public ResponseEntity<Void> addAbility(@PathVariable Long id, @PathVariable Long abilityId) {
        Pokemon pokemon = requirePokemon(id);
        Ability ability = abilityRepository.findById(abilityId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ability not found"));
        if (pokemon.getAbilities() == null) pokemon.setAbilities(new HashSet<>());
        pokemon.getAbilities().add(ability); pokemonRepository.save(pokemon);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @DeleteMapping("/{id}/abilities/{abilityId}")
    public ResponseEntity<Void> removeAbility(@PathVariable Long id, @PathVariable Long abilityId) {
        Pokemon pokemon = requirePokemon(id);
        Ability ability = abilityRepository.findById(abilityId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ability not found"));
        if (pokemon.getAbilities() == null || !pokemon.getAbilities().remove(ability)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ability is not assigned to this Pokemon");
        pokemonRepository.save(pokemon); return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}/abilities")
    public ResponseEntity<Set<Ability>> getAbilities(@PathVariable Long id) {
        Pokemon pokemon = requirePokemon(id);
        return ResponseEntity.ok(pokemon.getAbilities() == null ? Set.of() : pokemon.getAbilities());
    }

    private Pokemon requirePokemon(Long id) {
        return pokemonRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pokemon not found"));
    }
}
