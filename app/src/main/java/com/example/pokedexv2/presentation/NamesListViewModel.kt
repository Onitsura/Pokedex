package com.example.pokedexv2.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.PokemonDetails
import com.example.domain.repository.PokemonRepository
import com.example.domain.usecase.GetPokemonsNamesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.*

@HiltViewModel
class NamesListViewModel @Inject constructor(private val getPokemonsNamesUseCase: GetPokemonsNamesUseCase, pokemonRepository: PokemonRepository) :
    ViewModel() {

    val liveData: MutableLiveData<String> = MutableLiveData()

    init {
        viewModelScope.launch {
            pokemonRepository.pokemonDetailsFlow.await().flowOn(Dispatchers.Default)
                .collect { pokemonDetails ->
                    liveData.value = pokemonDetails
                    Log.e("Flow", "receive = $pokemonDetails")
                }
        }
    }

















}