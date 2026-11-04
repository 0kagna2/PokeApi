Create Database pokeapi;

USE pokeapi;

CREATE TABLE pokeapi.pokemons (
    pokemon_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    pokemon_name VARCHAR(255),
    pokemon_description VARCHAR(255),
    pokemon_type VARCHAR(255)
);

