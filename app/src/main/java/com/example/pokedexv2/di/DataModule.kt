package com.example.pokedexv2.di

import android.content.Context
import androidx.room.Room
import com.example.domain.repository.PokemonRepository
import com.example.pokedexv2.data.repository.PokemonRepositoryImpl
import com.example.pokedexv2.data.retrofit.RemoteDataSource
import com.example.pokedexv2.data.room.RoomPokemonStorage
import com.example.pokedexv2.data.room.database.AppDatabase
import com.example.pokedexv2.data.storage.PokemonStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "pokemon_details_database")
            .build()
    }

    @Provides
    @Singleton
    fun provideRoomPokemonStorage(database: AppDatabase): PokemonStorage {
        return RoomPokemonStorage(database = database)
    }

    @Provides
    @Singleton
    fun providePokemonRepository(pokemonStorage: PokemonStorage, remoteDataSource: RemoteDataSource): PokemonRepository {
        return PokemonRepositoryImpl(pokemonStorage = pokemonStorage, remoteDataSource = remoteDataSource)
    }




}