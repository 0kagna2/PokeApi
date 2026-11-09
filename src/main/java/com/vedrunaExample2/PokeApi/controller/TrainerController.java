package com.vedrunaExample2.PokeApi.controller;

import com.vedrunaExample2.PokeApi.persistance.model.Pokemon;
import com.vedrunaExample2.PokeApi.persistance.model.Trainer;
import com.vedrunaExample2.PokeApi.persistance.repository.PokemonRepository;
import com.vedrunaExample2.PokeApi.persistance.repository.TrainerRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController @RequestMapping("/api/v1/trainers") @CrossOrigin @AllArgsConstructor
public class TrainerController {
    private final TrainerRepository trainerRepository;
    private final PokemonRepository pokemonRepository;

    @GetMapping public ResponseEntity<List<Trainer>> all() { return ResponseEntity.ok(trainerRepository.findAll()); }
    @GetMapping("/{id}") public ResponseEntity<Trainer> one(@PathVariable Long id) { return ResponseEntity.ok(requireTrainer(id)); }
    @PostMapping public ResponseEntity<Trainer> create(@RequestBody Trainer trainer) { trainer.setId(null); return ResponseEntity.status(HttpStatus.CREATED).body(trainerRepository.save(trainer)); }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) { requireTrainer(id); trainerRepository.deleteById(id); return ResponseEntity.noContent().build(); }

    @PutMapping("/{trainerId}/pokemons/{pokemonId}")
    public ResponseEntity<Pokemon> assign(@PathVariable Long trainerId, @PathVariable Long pokemonId) {
        Trainer trainer = requireTrainer(trainerId);
        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pokemon not found"));
        pokemon.setTrainer(trainer);
        return ResponseEntity.ok(pokemonRepository.save(pokemon));
    }
    @GetMapping("/{id}/pokemons")
    public ResponseEntity<List<Pokemon>> pokemons(@PathVariable Long id) { requireTrainer(id); return ResponseEntity.ok(pokemonRepository.findByTrainerId(id)); }

    @GetMapping("/{id}/summary")
    public ResponseEntity<Map<String, Object>> summary(@PathVariable Long id) {
        Trainer trainer = requireTrainer(id);
        List<Pokemon> pokemons = pokemonRepository.findByTrainerId(id);
        Set<String> types = pokemons.stream().map(Pokemon::getType).filter(t -> t != null).collect(Collectors.toSet());
        return ResponseEntity.ok(Map.of("trainer", trainer, "pokemonCount", pokemons.size(), "types", types));
    }
    private Trainer requireTrainer(Long id) { return trainerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Trainer not found")); }
}
