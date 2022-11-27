package com.example.pokedexv2.data.room

import com.example.pokedexv2.data.retrofit.ApiService
import com.example.pokedexv2.data.retrofit.RemoteDataSource
import com.example.pokedexv2.data.retrofit.models.PokemonDetailsById
import com.example.pokedexv2.data.room.database.AppDatabase
import com.example.pokedexv2.data.storage.PokemonStorage
import com.example.pokedexv2.data.storage.models.StoragePokemonDetails
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import javax.inject.Inject
import kotlin.collections.HashMap

class RoomPokemonStorage @Inject constructor(private val database: AppDatabase, remoteDataSource: RemoteDataSource) : PokemonStorage {






    override fun saveAll(pokemonDetails: StoragePokemonDetails) {

    }

    override fun getDetailsById(id: Long): StoragePokemonDetails {
        TODO("Not yet implemented")
    }




}