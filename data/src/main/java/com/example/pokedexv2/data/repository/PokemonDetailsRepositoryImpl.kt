package com.example.pokedexv2.data.repository

import com.example.domain.models.PokemonDetails
import com.example.domain.repository.PokemonDetailsRepository
import com.example.pokedexv2.data.storage.PokemonStorage
import com.example.pokedexv2.data.storage.models.StoragePokemonDetails

class PokemonDetailsRepositoryImpl(private val pokemonStorage: PokemonStorage) :
    PokemonDetailsRepository {

    override fun saveDetails(pokemonDetails: PokemonDetails) {
        pokemonStorage.saveAll(mapToStorage(pokemonDetails))
    }

    override fun getDetailsById(id: Long): PokemonDetails {
        val pokemon = pokemonStorage.getDetailsById(id = id)
        return mapToDomain(pokemon)
    }

    private fun mapToDomain(storage: StoragePokemonDetails): PokemonDetails {
        return PokemonDetails(
            id = storage.id,
            health = storage.health,
            urlAddress = storage.urlAddress,
            attack = storage.attack,
            experience = storage.experience,
            name = storage.name
        )
    }

    private fun mapToStorage(pokemon: PokemonDetails): StoragePokemonDetails {
        return StoragePokemonDetails(
            id = pokemon.id,
            health = pokemon.health,
            urlAddress = pokemon.urlAddress,
            attack = pokemon.attack,
            experience = pokemon.experience,
            name = pokemon.name
        )

    }


}