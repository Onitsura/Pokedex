package com.example.pokedexv2.data.di

import com.example.pokedexv2.data.repository.Mapper
import com.example.pokedexv2.data.retrofit.ApiService
import com.example.pokedexv2.data.retrofit.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideClient(): OkHttpClient {
        return OkHttpClient()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://pokeapi.co/api/v2/")
            .build()
            .create(ApiService::class.java)
    }


    @Provides
    @Singleton
    fun provideRemoteDataSource(apiService: ApiService, mapper: Mapper): RemoteDataSource {
        return RemoteDataSource(apiService = apiService, mapper = mapper)
    }


}