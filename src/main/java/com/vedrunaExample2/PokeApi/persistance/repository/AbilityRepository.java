package com.vedrunaExample2.PokeApi.persistance.repository;
import com.vedrunaExample2.PokeApi.persistance.model.Ability;
import org.springframework.data.jpa.repository.JpaRepository;
public interface AbilityRepository extends JpaRepository<Ability, Long> {}
