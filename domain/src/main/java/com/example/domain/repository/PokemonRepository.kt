package com.example.domain.repository

import com.example.domain.models.PokemonDetails
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    val pokemonDetailsFlow: Deferred<Flow<String>>

    fun saveDetails(pokemonDetails: PokemonDetails)

    fun getDetailsById(id: Long): PokemonDetails

    fun getNamesRemote(): List<String>
}