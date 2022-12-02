package com.example.pokedexv2.presentation


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    val id: MutableLiveData<Long> = MutableLiveData()
    val name: MutableLiveData<String> = MutableLiveData()
    val experience: MutableLiveData<String> = MutableLiveData()
    val health: MutableLiveData<String> = MutableLiveData()
    val urlAddress: MutableLiveData<String> = MutableLiveData()
    val attack: MutableLiveData<String> = MutableLiveData()


    init {
        viewModelScope.launch {

            if (id.value != null) {
                viewModelScope.launch {
                    pokemonRepository.getDetailsById(id = id.value!!)
                        .flowOn(Dispatchers.Default).collect {
                            experience.value = it.experience
                            health.value = it.health
                            name.value = capitalizeFirst(it.name)
                            urlAddress.value = it.urlAddress
                            attack.value = it.attack
                        }

                }
            }
        }
    }

    fun load() {
        viewModelScope.launch {
            pokemonRepository.getDetailsById(
                id = id.value!!
            )
                .flowOn(Dispatchers.Default).collect {
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