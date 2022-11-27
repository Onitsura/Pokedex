package com.example.pokedexv2.data.repository

import com.example.domain.models.PokemonDetails
import com.example.domain.repository.PokemonRepository
import com.example.pokedexv2.data.retrofit.RemoteDataSource
import com.example.pokedexv2.data.storage.PokemonStorage
import com.example.pokedexv2.data.storage.models.StoragePokemonDetails
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonStorage: PokemonStorage,
    val remoteDataSource: RemoteDataSource
) :
    PokemonRepository {

    override fun saveDetails(pokemonDetails: PokemonDetails) {
        pokemonStorage.saveAll(mapToStorage(pokemonDetails))
    }

    override fun getDetailsById(id: Long): PokemonDetails {
        val pokemon = pokemonStorage.getDetailsById(id = id)
        return mapToDomain(pokemon)
    }

    override suspend fun getNamesRemote(): Flow<String> {

        return remoteDataSource.loadNames(remoteDataSource.apiService).asFlow()


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