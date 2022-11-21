package com.example.pokedexv2.retrofit

import com.example.pokedexv2.retrofit.models.PokemonDetails
import com.example.pokedexv2.retrofit.models.PokemonNameAndId
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("pokedex/1")
    fun getNames(): Call<PokemonNameAndId>

    @GET("pokemon/{id}")
    suspend fun getDetailsById(@Path(value = "id") id: String): Call<PokemonDetails>
}