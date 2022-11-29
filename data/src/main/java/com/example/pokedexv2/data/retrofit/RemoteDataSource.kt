package com.example.pokedexv2.data.retrofit

import android.util.Log
import com.example.pokedexv2.data.repository.Mapper
import com.example.pokedexv2.data.retrofit.models.PokemonDetailsById
import com.example.pokedexv2.data.retrofit.models.PokemonEntries
import com.example.pokedexv2.data.retrofit.models.PokemonNameAndId
import com.example.pokedexv2.data.storage.models.StorageNameAndUrl
import com.example.pokedexv2.data.storage.models.StoragePokemonDetails
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class RemoteDataSource @Inject constructor(val apiService: ApiService, private val mapper: Mapper) {


    suspend fun loadNames(apiService: ApiService): MutableList<String> {
        val names: MutableList<String> = mutableListOf()
        val receive: List<PokemonEntries> = (apiService.getNames().pokemon_entries)
        receive.map {
            names.add(it.species.name)
        }
        return names
    }


    suspend fun loadInfo(
        apiService: ApiService,
        name: String
    ): StoragePokemonDetails {
        return mapper.mapToPokemon(apiService.getDetailsById(name))
    }


    suspend fun loadNamesSprites(apiService: ApiService): MutableList<StorageNameAndUrl> {
        val map: MutableList<StorageNameAndUrl> = mutableListOf()
        val receive: List<PokemonEntries> = (apiService.getNames().pokemon_entries)
        receive.map {
            map.add(
                StorageNameAndUrl(
                    name = it.species.name,
                    urlAddress = loadInfo(apiService, it.species.name).urlAddress
                )
            )
            Log.e("Flow", map.size.toString())
        }
        return map
    }


}




