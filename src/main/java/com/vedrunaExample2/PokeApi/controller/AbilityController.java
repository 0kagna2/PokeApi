package com.vedrunaExample2.PokeApi.controller;

import com.vedrunaExample2.PokeApi.persistance.model.Ability;
import com.vedrunaExample2.PokeApi.persistance.model.Pokemon;
import com.vedrunaExample2.PokeApi.persistance.repository.AbilityRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController @RequestMapping("/api/v1/abilities") @CrossOrigin @AllArgsConstructor
public class AbilityController {
    private final AbilityRepository abilityRepository;
    @PostMapping public ResponseEntity<Ability> create(@RequestBody Ability ability) { ability.setId(null); return ResponseEntity.status(HttpStatus.CREATED).body(abilityRepository.save(ability)); }
    @GetMapping public ResponseEntity<List<Ability>> all() { return ResponseEntity.ok(abilityRepository.findAll()); }
    @GetMapping("/{id}/pokemons")
    public ResponseEntity<List<Pokemon>> pokemons(@PathVariable Long id) {
        Ability ability = abilityRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ability not found"));
        return ResponseEntity.ok(ability.getPokemons() == null ? List.of() : List.copyOf(ability.getPokemons()));
    }
}
