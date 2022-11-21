package com.example.domain.repository

import com.example.domain.models.PokemonDetails

interface PokemonDetailsRepository {

    fun saveDetails(pokemonDetails: PokemonDetails)

    fun getDetailsById(id: Long): PokemonDetails
}