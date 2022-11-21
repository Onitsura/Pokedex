package com.example.domain.usecase

import com.example.domain.models.PokemonDetails
import com.example.domain.repository.PokemonDetailsRepository

class GetPokemonDetailsByIdUseCase(private val pokemonDetailsRepository: PokemonDetailsRepository) {

    fun execute(id: Long): PokemonDetails{
        return pokemonDetailsRepository.getDetailsById(id = id)
    }

}