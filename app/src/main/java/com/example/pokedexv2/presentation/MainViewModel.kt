package com.example.pokedexv2.presentation

import androidx.lifecycle.ViewModel
import com.example.domain.usecase.GetPokemonDetailsByIdUseCase
import com.example.domain.usecase.GetPokemonsNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val getPokemonsNameUseCase: GetPokemonsNameUseCase,
    val getPokemonDetailsByIdUseCase: GetPokemonDetailsByIdUseCase
) : ViewModel() {








}