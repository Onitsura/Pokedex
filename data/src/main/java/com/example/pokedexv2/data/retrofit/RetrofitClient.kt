package com.example.pokedexv2.data.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val client = OkHttpClient()




    val retrofit = Retrofit.Builder()
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://pokeapi.co/api/v2/")
        .build()
        .create(ApiService::class.java)
}