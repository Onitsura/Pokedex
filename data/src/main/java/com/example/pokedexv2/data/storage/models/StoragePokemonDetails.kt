package com.example.pokedexv2.data.storage.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class StoragePokemonDetails(
    @PrimaryKey
    val name: String,

//    val id: Long,

    val attack: String,

    val health: String,

    val experience: String,

    val urlAddress: String
)