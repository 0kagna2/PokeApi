package com.vedrunaExample2.PokeApi.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vedrunaExample2.PokeApi.persistance.model.Pokemon;
import java.util.List;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
    List<Pokemon> findByTrainerId(Long trainerId);

}
