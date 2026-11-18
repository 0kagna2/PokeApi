USE pokemondb;

-- Datos de ejemplo para probar las tres relaciones.
INSERT INTO trainers (name, hometown)
SELECT 'Ash', 'Pueblo Paleta' FROM DUAL
WHERE NOT EXISTS (SELECT 1 FROM trainers WHERE name = 'Ash');

INSERT INTO pokemon (name, imagen_url, `type`)
SELECT 'Pikachu', NULL, 'Electric' FROM DUAL
WHERE NOT EXISTS (SELECT 1 FROM pokemon WHERE name = 'Pikachu');

INSERT INTO pokemon_stats (hp, attack, defense, speed, height, weight, pokemon_id)
SELECT 35, 55, 40, 90, 0.40, 6.00, p.id
FROM pokemon p
WHERE p.name = 'Pikachu'
  AND NOT EXISTS (SELECT 1 FROM pokemon_stats ps WHERE ps.pokemon_id = p.id);

INSERT INTO abilities (name, description)
SELECT 'Static', 'Puede paralizar al rival al contacto'
FROM DUAL
WHERE NOT EXISTS (SELECT 1 FROM abilities WHERE name = 'Static');

UPDATE pokemon
SET trainer_id = (SELECT id FROM trainers WHERE name = 'Ash' LIMIT 1)
WHERE name = 'Pikachu' AND trainer_id IS NULL;

INSERT INTO pokemon_abilities (pokemon_id, ability_id)
SELECT p.id, a.id
FROM pokemon p CROSS JOIN abilities a
WHERE p.name = 'Pikachu' AND a.name = 'Static'
  AND NOT EXISTS (
      SELECT 1 FROM pokemon_abilities pa
      WHERE pa.pokemon_id = p.id AND pa.ability_id = a.id
  );
