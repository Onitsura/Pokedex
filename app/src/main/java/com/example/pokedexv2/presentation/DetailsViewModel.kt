package com.example.pokedexv2.presentation


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.PokemonDetails
import com.example.domain.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor(private val pokemonRepository: PokemonRepository) :
    ViewModel() {

    val name: MutableLiveData<String> = MutableLiveData()
    val experience: MutableLiveData<String> = MutableLiveData()
    val health: MutableLiveData<String> = MutableLiveData()
    val urlAddress: MutableLiveData<String> = MutableLiveData()
    val attack: MutableLiveData<String> = MutableLiveData()
    val entity: MutableLiveData<PokemonDetails> = MutableLiveData()


    init {
        viewModelScope.launch {

            Log.e("trans3",
                name.value.toString().replaceFirstChar { it.lowercase(Locale.getDefault()) })
            if (name.value != null) {
                viewModelScope.launch {
                    Log.e("trans3",
                        name.value.toString()
                            .replaceFirstChar { it.lowercase(Locale.getDefault()) })
                    pokemonRepository.getDetailsByName(name = name.value.toString()
                        .replaceFirstChar { it.lowercase(Locale.getDefault()) })
                        .flowOn(Dispatchers.Default)
                        .collect {
                            experience.value = it.experience
                            health.value = it.health
                            name.value = capitalizeFirst(it.name)
                            urlAddress.value = it.urlAddress
                            attack.value = it.attack
                        }

                }
            } else {
                Log.e("transFail", "${name.value}")
            }

//            pokemonRepository.getDetailsByName(name = (name.value.toString()).replaceFirstChar {
//                it.lowercase(
//                    Locale.getDefault()
//                )
//            })
//                .flowOn(Dispatchers.IO)
//                .collect{
//                    experience.value = it.experience
//                    health.value = it.health
//                    name.value = it.name
//                    urlAddress.value = it.urlAddress
//                    attack.value = it.attack
//                }
//
        }
    }

    fun load() {
        viewModelScope.launch {
            Log.e("trans3",
                name.value.toString().replaceFirstChar { it.lowercase(Locale.getDefault()) })
            pokemonRepository.getDetailsByName(name = name.value.toString()
                .replaceFirstChar { it.lowercase(Locale.getDefault()) })
                .flowOn(Dispatchers.Default)
                .collect {
                    experience.value = it.experience
                    health.value = it.health
                    name.value = capitalizeFirst(it.name)
                    urlAddress.value = it.urlAddress
                    attack.value = it.attack
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