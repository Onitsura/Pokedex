package com.example.pokedexv2.presentation


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.NameAndId
import com.example.domain.repository.PokemonRepository
import com.example.domain.usecase.GetPokemonsNamesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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
    private val nameAndId: MutableMap<String, Long> = mutableMapOf()






    init {
        viewModelScope.launch {
            pokemonRepository.getNamesRemote().flowOn(Dispatchers.IO).collect { it: NameAndId ->
                namesLiveData.value = it.name
                nameAndId[it.name] = it.id
                allNames.value!!.add(capitalizeFirst(it.name))
                allNames.value!!.sorted()
            }
        }
    }



    fun getId(name: String): Long{
        var a: Long = 0
        if(nameAndId.containsKey(name.replaceFirstChar { it.lowercase(Locale.getDefault()) })){
            a = nameAndId[name.replaceFirstChar { it.lowercase(Locale.getDefault()) }]!!.toLong()
        }

     return a
    }



    private fun capitalizeFirst(it: String): String {
        return it.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(Locale.getDefault())
            else it.toString()
        }
    }

    private fun decapitalizeFirst(it: String): String {
        return it.replaceFirstChar {
            if (it.isTitleCase()) it.lowercase(Locale.getDefault())
            else it.toString()
        }
    }


}