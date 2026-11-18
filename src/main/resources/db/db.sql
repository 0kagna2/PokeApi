-- ============================================================
--  Práctica · API REST de Pokémon (relaciones entre tablas)
--  Script de base de datos (solo esquema, sin datos)
--  Motor: MySQL 8.x
-- ============================================================
--  Uso:   mysql -u root -p < pokemon_schema.sql
--  (o ejecutarlo desde MySQL Workbench / DBeaver)
-- ============================================================

CREATE DATABASE IF NOT EXISTS pokemondb
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE pokemondb;

-- ------------------------------------------------------------
--  Entrenadores
-- ------------------------------------------------------------
CREATE TABLE IF NOT EXISTS trainers (
    id       BIGINT       NOT NULL AUTO_INCREMENT,
    name     VARCHAR(45)  NOT NULL,
    hometown VARCHAR(45),
    PRIMARY KEY (id)
) ENGINE = InnoDB;

-- ------------------------------------------------------------
--  Pokémon
--  Relación 1:n con trainers: un entrenador tiene muchos Pokémon y
--  cada Pokémon tiene como mucho un entrenador (trainer_id puede ser
--  NULL: un Pokémon salvaje no tiene entrenador).
-- ------------------------------------------------------------
CREATE TABLE IF NOT EXISTS pokemon (
    id         BIGINT        NOT NULL AUTO_INCREMENT,
    name       VARCHAR(45)   NOT NULL,
    imagen_url VARCHAR(255),
    `type`     VARCHAR(45),
    trainer_id BIGINT,
    PRIMARY KEY (id),
    CONSTRAINT fk_pokemon_trainer
        FOREIGN KEY (trainer_id) REFERENCES trainers (id)
) ENGINE = InnoDB;

-- ------------------------------------------------------------
--  Estadísticas de un Pokémon
--  Relación 1:1 con pokemon: pokemon_id es UNIQUE, así que un
--  Pokémon no puede tener dos fichas.
-- ------------------------------------------------------------
CREATE TABLE IF NOT EXISTS pokemon_stats (
    id         BIGINT  NOT NULL AUTO_INCREMENT,
    hp         INT     NOT NULL,
    attack     INT     NOT NULL,
    defense    INT     NOT NULL,
    speed      INT     NOT NULL,
    height     DOUBLE,
    weight     DOUBLE,
    pokemon_id BIGINT  NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uq_stats_pokemon UNIQUE (pokemon_id),
    CONSTRAINT fk_stats_pokemon
        FOREIGN KEY (pokemon_id) REFERENCES pokemon (id)
) ENGINE = InnoDB;

-- ------------------------------------------------------------
--  Habilidades
-- ------------------------------------------------------------
CREATE TABLE IF NOT EXISTS abilities (
    id          BIGINT        NOT NULL AUTO_INCREMENT,
    name        VARCHAR(45)   NOT NULL,
    description VARCHAR(255),
    PRIMARY KEY (id)
) ENGINE = InnoDB;

-- ------------------------------------------------------------
--  Tabla intermedia de la relación n:m pokemon <-> abilities
--  Solo contiene las dos claves foráneas (clave primaria compuesta).
--  No lleva ON DELETE CASCADE a propósito: al borrar un Pokémon o una
--  habilidad que están relacionados, MySQL rechazará el borrado y la
--  API debe controlar ese caso.
-- ------------------------------------------------------------
CREATE TABLE IF NOT EXISTS pokemon_abilities (
    pokemon_id BIGINT NOT NULL,
    ability_id BIGINT NOT NULL,
    PRIMARY KEY (pokemon_id, ability_id),
    CONSTRAINT fk_pa_pokemon
        FOREIGN KEY (pokemon_id) REFERENCES pokemon (id),
    CONSTRAINT fk_pa_ability
        FOREIGN KEY (ability_id) REFERENCES abilities (id)
) ENGINE = InnoDB;