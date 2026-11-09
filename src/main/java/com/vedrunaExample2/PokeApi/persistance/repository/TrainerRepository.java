package com.vedrunaExample2.PokeApi.persistance.repository;
import com.vedrunaExample2.PokeApi.persistance.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
public interface TrainerRepository extends JpaRepository<Trainer, Long> {}
