package com.example.pokedexv2.data.room

import com.example.pokedexv2.data.room.database.AppDatabase
import com.example.pokedexv2.data.storage.PokemonStorage
import com.example.pokedexv2.data.storage.models.StoragePokemonDetails

class RoomPokemonStorage(private val database: AppDatabase) : PokemonStorage {






    override fun saveAll(pokemonDetails: StoragePokemonDetails) {
        TODO("Not yet implemented")
    }

    override fun getDetailsById(id: Long): StoragePokemonDetails {
        TODO("Not yet implemented")
    }
}