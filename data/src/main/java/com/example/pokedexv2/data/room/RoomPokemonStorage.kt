package com.example.pokedexv2.data.room

import com.example.pokedexv2.data.retrofit.RemoteDataSource
import com.example.pokedexv2.data.room.database.AppDatabase
import com.example.pokedexv2.data.storage.PokemonStorage
import com.example.pokedexv2.data.storage.models.StoragePokemonDetails
import javax.inject.Inject

class RoomPokemonStorage @Inject constructor(
    private val database: AppDatabase,
    remoteDataSource: RemoteDataSource
) : PokemonStorage {


    override fun saveAll(pokemonDetails: StoragePokemonDetails) {

    }

    override fun getDetailsById(name: String): StoragePokemonDetails {
        TODO("Not yet implemented")
    }


}