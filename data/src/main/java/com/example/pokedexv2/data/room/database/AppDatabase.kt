package com.example.pokedexv2.data.room.database

import androidx.room.Database
import com.example.pokedexv2.data.room.dao.StoragePokemonDetailsDao
import com.example.pokedexv2.data.storage.models.StoragePokemonDetails

@Database(entities = [StoragePokemonDetails::class], version = 1)
abstract class AppDatabase {
    abstract fun StoragePokemonDetailsDao(): StoragePokemonDetailsDao
}