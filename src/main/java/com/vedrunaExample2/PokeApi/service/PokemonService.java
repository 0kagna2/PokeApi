package com.vedrunaExample2.PokeApi.service;
import com.vedrunaExample2.PokeApi.persistance.model.Pokemon;
import java.util.List;
public interface PokemonService {
    public List<Pokemon> getAllPokemons();
    public Pokemon getPokemonById(long id);
    public Pokemon createPokemon(Pokemon pokemon);
    public Pokemon updatePokemon(long id, Pokemon pokemon);
    public void deletePokemon(long id);
}
