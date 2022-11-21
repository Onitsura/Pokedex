package com.example.pokedexv2.data.storage.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class StoragePokemonDetails(
    @PrimaryKey
    val id: Long,
    @ColumnInfo(name = "name")
    val name: String,

    val attack: String,

    val health: String,

    val experience: String,

    val urlAddress: String
)