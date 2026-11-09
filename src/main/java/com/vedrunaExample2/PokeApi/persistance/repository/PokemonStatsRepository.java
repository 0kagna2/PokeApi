package com.vedrunaExample2.PokeApi.persistance.repository;
import com.vedrunaExample2.PokeApi.persistance.model.PokemonStats;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface PokemonStatsRepository extends JpaRepository<PokemonStats, Long> { Optional<PokemonStats> findByPokemonId(Long pokemonId); }
