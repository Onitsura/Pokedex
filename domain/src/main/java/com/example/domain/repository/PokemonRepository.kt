package com.example.domain.repository

import com.example.domain.models.NameAndUrl
import com.example.domain.models.PokemonDetails
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    fun saveDetails(pokemonDetails: PokemonDetails)

    suspend fun getDetailsByName(name: String): Flow<PokemonDetails>

    suspend fun getNamesRemote(): Flow<String>

    suspend fun getNamesSprites(): Flow<NameAndUrl>
}