package com.example.pokedexv2.presentation


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.domain.repository.PokemonRepository
import com.example.domain.usecase.GetPokemonsNamesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class NamesListViewModel @Inject constructor(private val getPokemonsNamesUseCase: GetPokemonsNamesUseCase,val pokemonRepository: PokemonRepository) :
    ViewModel() {

    val allNames: MutableLiveData<MutableList<String>> = MutableLiveData(mutableListOf())
    val namesLiveData: MutableLiveData<String> = MutableLiveData()
    val urlLiveData: MutableLiveData<String> = MutableLiveData()
    val nameClicked: MutableLiveData<String> = MutableLiveData()


    init {
        viewModelScope.launch {

            pokemonRepository.getNamesRemote()
                .flowOn(Dispatchers.IO)
                .collect { name ->
                    namesLiveData.value = name
                    allNames.value!!.add(capitalizeFirst(name))
                    allNames.value!!.sorted()


                }



            //Метод по получению урл и имени


        }
    }



    fun getSprite(pokemon: String){
        viewModelScope.launch {
            pokemonRepository.getDetailsByName(pokemon)
                .flowOn(Dispatchers.IO)
                .collect {
                    urlLiveData.value = it.urlAddress
                }
        }
    }



    private fun capitalizeFirst(it: String): String {
        return it.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(Locale.getDefault())
            else it.toString()
        }
    }
    private fun decapitalizeFirst(it: String): String{
        return it.replaceFirstChar {
            if (it.isTitleCase()) it.lowercase(Locale.getDefault())
            else it.toString()
        }
    }
















}