USE pokeapi;

-- Datos de ejemplo para probar las relaciones. Las comprobaciones evitan duplicados.
INSERT INTO pokemons (pokemon_name, pokemon_description, pokemon_type)
SELECT 'Pikachu', 'Small electric mouse', 'Electric' FROM DUAL
WHERE NOT EXISTS (SELECT 1 FROM pokemons WHERE pokemon_name = 'Pikachu');

INSERT INTO trainers (name, hometown)
SELECT 'Ash', 'Pueblo Paleta' FROM DUAL
WHERE NOT EXISTS (SELECT 1 FROM trainers WHERE name = 'Ash');

INSERT INTO abilities (name, description)
SELECT 'Static', 'Puede paralizar al rival al contacto' FROM DUAL
WHERE NOT EXISTS (SELECT 1 FROM abilities WHERE name = 'Static');

INSERT INTO pokemon_stats (hp, attack, defense, speed, height, weight, pokemon_id)
SELECT 35, 55, 40, 90, 0.40, 6.00, p.pokemon_id
FROM pokemons p
WHERE p.pokemon_name = 'Pikachu'
  AND NOT EXISTS (SELECT 1 FROM pokemon_stats ps WHERE ps.pokemon_id = p.pokemon_id);

UPDATE pokemons
SET trainer_id = (SELECT id FROM trainers WHERE name = 'Ash' LIMIT 1)
WHERE pokemon_name = 'Pikachu' AND trainer_id IS NULL;

INSERT INTO pokemon_abilities (pokemon_id, ability_id)
SELECT p.pokemon_id, a.id
FROM pokemons p CROSS JOIN abilities a
WHERE p.pokemon_name = 'Pikachu' AND a.name = 'Static'
  AND NOT EXISTS (
      SELECT 1 FROM pokemon_abilities pa
      WHERE pa.pokemon_id = p.pokemon_id AND pa.ability_id = a.id
  );
