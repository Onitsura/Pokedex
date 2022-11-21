package com.example.pokedexv2.di

import com.example.domain.repository.PokemonRepository
import com.example.domain.usecase.GetPokemonDetailsByIdUseCase
import com.example.domain.usecase.GetPokemonsNameUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideGetPokemonsNameUseCase(pokemonRepository: PokemonRepository): GetPokemonsNameUseCase {
        return GetPokemonsNameUseCase(pokemonRepository = pokemonRepository)
    }

    @Provides
    fun provideGetPokemonDetailsByIdUseCase(pokemonRepository: PokemonRepository): GetPokemonDetailsByIdUseCase {
        return GetPokemonDetailsByIdUseCase(pokemonRepository = pokemonRepository)
    }


}