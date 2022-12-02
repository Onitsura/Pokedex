package com.example.pokedexv2.data.retrofit

import android.util.Log
import com.example.domain.models.NameAndId
import com.example.pokedexv2.data.repository.Mapper
import com.example.pokedexv2.data.retrofit.models.PokemonEntries
import com.example.pokedexv2.data.storage.models.StoragePokemonDetails
import javax.inject.Inject


class RemoteDataSource @Inject constructor(val apiService: ApiService, private val mapper: Mapper) {


    suspend fun loadNames(apiService: ApiService): MutableList<NameAndId> {
        val names: MutableList<String> = mutableListOf()
        val nameAndId: MutableList<NameAndId> = mutableListOf()
        val receive: List<PokemonEntries> = (apiService.getNames().pokemon_entries)
        receive.map {
            names.add(it.species.name)
        }
        receive.map{
            nameAndId.add(NameAndId(name = it.species.name, id = it.entry_number))
        }
        return nameAndId
    }


    suspend fun loadInfo(
        apiService: ApiService,
        id: Long
    ): StoragePokemonDetails {
        return mapper.mapToPokemon(apiService.getDetailsById(id = id))
    }





}




