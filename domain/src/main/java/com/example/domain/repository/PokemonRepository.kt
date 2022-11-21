package com.example.domain.repository

import com.example.domain.models.PokemonDetails

interface PokemonRepository {

    fun saveDetails(pokemonDetails: PokemonDetails)

    fun getDetailsById(id: Long): PokemonDetails

    fun getNamesRemote(): List<String>
}