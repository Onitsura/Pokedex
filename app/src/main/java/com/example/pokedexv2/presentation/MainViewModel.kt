package com.example.pokedexv2.presentation

import androidx.lifecycle.ViewModel
import com.example.domain.usecase.GetPokemonDetailsByIdUseCase
import com.example.domain.usecase.GetPokemonsNamesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val getPokemonsNamesUseCase: GetPokemonsNamesUseCase,
    val getPokemonDetailsByIdUseCase: GetPokemonDetailsByIdUseCase
) : ViewModel() {








}