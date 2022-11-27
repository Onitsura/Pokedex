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
import java.util.*
import javax.inject.Inject
import kotlin.coroutines.*

@HiltViewModel
class NamesListViewModel @Inject constructor(private val getPokemonsNamesUseCase: GetPokemonsNamesUseCase, pokemonRepository: PokemonRepository) :
    ViewModel() {

    val allNames: MutableLiveData<MutableList<String>> = MutableLiveData(mutableListOf())
    val namesLiveData: MutableLiveData<String> = MutableLiveData()


    init {
        viewModelScope.launch {

            pokemonRepository.getNamesRemote()
                .flowOn(Dispatchers.IO)
                .collect { name ->
                    namesLiveData.value = capitalizeFirst(name)
                    allNames.value!!.add(capitalizeFirst(name))
                    allNames.value!!.sorted()
                }
        }


    }
    private fun capitalizeFirst(it: String): String {
        return it.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(Locale.getDefault())
            else it.toString()
        }
    }
















}