package com.example.pokedexv2.data.retrofit.models

import com.google.gson.annotations.SerializedName

data class PokemonDetailsById(
    @SerializedName("name")
    val name: String,
    @SerializedName("sprites")
    val sprite: Sprite,
    @SerializedName("stats")
    val stats: List<Stats>,
    @SerializedName("base_experience")
    val exp: String
)

data class Stats(
    @SerializedName("base_stat")
    val base_stat: String,
    @SerializedName("stat")
    val name_stat: NameStat
)

data class NameStat(
    @SerializedName("name")
    val name_stat: String
)

data class Sprite(
    @SerializedName("front_default")
    val front: String
)
