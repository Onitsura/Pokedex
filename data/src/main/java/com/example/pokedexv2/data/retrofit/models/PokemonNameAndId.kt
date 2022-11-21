package com.example.pokedexv2.data.retrofit.models

import com.google.gson.annotations.SerializedName

data class PokemonNameAndId(
    @SerializedName("pokemon_entries")
    val pokemon_entries: List<PokemonEntries>,
    @SerializedName("id")
    val id: String
)

data class PokemonEntries(
    @SerializedName("pokemon_species")
    val species: PokemonName
)

data class PokemonName(
    @SerializedName("name")
    val name: String,
)


