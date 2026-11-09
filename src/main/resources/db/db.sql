CREATE DATABASE IF NOT EXISTS pokeapi;
USE pokeapi;

CREATE TABLE IF NOT EXISTS trainers (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    hometown VARCHAR(255)
);

-- Mantiene la tabla pokemons que ya utiliza esta API. Ejecutar este script una vez
-- para añadir las columnas de la práctica a una base de datos ya creada.
CREATE TABLE IF NOT EXISTS pokemons (
    pokemon_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    pokemon_name VARCHAR(255),
    pokemon_description VARCHAR(255),
    pokemon_type VARCHAR(255)
);

ALTER TABLE pokemons
    ADD COLUMN imagen_url VARCHAR(255),
    ADD COLUMN trainer_id BIGINT NULL,
    ADD CONSTRAINT fk_pokemons_trainer FOREIGN KEY (trainer_id) REFERENCES trainers(id);

CREATE TABLE IF NOT EXISTS pokemon_stats (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    hp INT NOT NULL,
    attack INT NOT NULL,
    defense INT NOT NULL,
    speed INT NOT NULL,
    height DECIMAL(8,2),
    weight DECIMAL(8,2),
    pokemon_id BIGINT NOT NULL UNIQUE,
    CONSTRAINT fk_stats_pokemon FOREIGN KEY (pokemon_id) REFERENCES pokemons(pokemon_id)
);

CREATE TABLE IF NOT EXISTS abilities (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT
);

CREATE TABLE IF NOT EXISTS pokemon_abilities (
    pokemon_id BIGINT NOT NULL,
    ability_id BIGINT NOT NULL,
    PRIMARY KEY (pokemon_id, ability_id),
    CONSTRAINT fk_pokemon_abilities_pokemon FOREIGN KEY (pokemon_id) REFERENCES pokemons(pokemon_id),
    CONSTRAINT fk_pokemon_abilities_ability FOREIGN KEY (ability_id) REFERENCES abilities(id)
);
