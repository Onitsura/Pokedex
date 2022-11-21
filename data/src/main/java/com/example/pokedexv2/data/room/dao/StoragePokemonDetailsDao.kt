package com.example.pokedexv2.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.pokedexv2.data.storage.models.StoragePokemonDetails

@Dao
interface StoragePokemonDetailsDao {
    @Query("SELECT * FROM storagepokemondetails WHERE id = :id")
    fun getById(id: Long): StoragePokemonDetails

    @Insert
    fun insert(storagePokemonDetails: StoragePokemonDetails)

}