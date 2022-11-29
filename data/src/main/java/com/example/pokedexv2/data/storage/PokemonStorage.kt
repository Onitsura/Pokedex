package com.example.pokedexv2.data.storage

import com.example.pokedexv2.data.storage.models.StoragePokemonDetails

interface PokemonStorage {

    fun saveAll(pokemonDetails: StoragePokemonDetails)

    fun getDetailsById(name: String): StoragePokemonDetails


}