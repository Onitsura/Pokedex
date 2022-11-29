package com.example.pokedexv2.data.repository

import android.util.Log
import com.example.domain.models.NameAndUrl
import com.example.domain.models.PokemonDetails
import com.example.domain.repository.PokemonRepository
import com.example.pokedexv2.data.retrofit.RemoteDataSource
import com.example.pokedexv2.data.storage.PokemonStorage
import com.example.pokedexv2.data.storage.models.StorageNameAndUrl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonStorage: PokemonStorage,
    private val remoteDataSource: RemoteDataSource,
    private val mapper: Mapper
) :
    PokemonRepository {

    override fun saveDetails(pokemonDetails: PokemonDetails) {
        pokemonStorage.saveAll(pokemonDetails = mapper.mapToStorageDetails(pokemonDetails = pokemonDetails))
    }

    override suspend fun getDetailsByName(name: String): Flow<PokemonDetails> {
//        val pokemonStorage = pokemonStorage.getDetailsById(name = name)
//        return mapper.mapToDomain(storage = pokemonStorage)
        return mapper.mapToDomainDetails(
            remoteDataSource.loadInfo(
                apiService = remoteDataSource.apiService,
                name = name
            )
        ).asFlow()

    }

    override suspend fun getNamesRemote(): Flow<String> {
        return remoteDataSource.loadNames(apiService = remoteDataSource.apiService).asFlow()
    }

    override suspend fun getNamesSprites(): Flow<NameAndUrl> {
        return mapper.mapToDomain(remoteDataSource.loadNamesSprites(apiService = remoteDataSource.apiService))
            .asFlow()
    }

}

private fun PokemonDetails.asFlow(): Flow<PokemonDetails> {
    val list = mutableListOf(this)
    return flow {
        emitAll(list.asFlow())
    }.flowOn(Dispatchers.IO)
}
