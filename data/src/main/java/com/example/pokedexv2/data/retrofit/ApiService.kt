package com.example.pokedexv2.data.retrofit

import com.example.pokedexv2.data.retrofit.models.PokemonDetailsById
import com.example.pokedexv2.data.retrofit.models.PokemonNameAndId
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {
    @GET("pokedex/1")
    suspend fun getNames(): PokemonNameAndId

    @GET("pokemon/{id}")
    suspend fun getDetailsById(@Path(value = "id") id: Long): PokemonDetailsById
}