package com.example.pokedexv2.data.repository

import com.example.domain.models.NameAndUrl
import com.example.domain.models.PokemonDetails
import com.example.pokedexv2.data.retrofit.models.PokemonDetailsById
import com.example.pokedexv2.data.storage.models.StorageNameAndUrl
import com.example.pokedexv2.data.storage.models.StoragePokemonDetails

private const val HEALTH_ID = 0
private const val ATTACK_ID = 1


class Mapper {


    fun mapToDomainDetails(storage: StoragePokemonDetails): PokemonDetails {
        return PokemonDetails(
            health = storage.health,
            urlAddress = storage.urlAddress,
            attack = storage.attack,
            experience = storage.experience,
            name = storage.name
        )
    }

    fun mapToStorageDetails(pokemonDetails: PokemonDetails): StoragePokemonDetails {
        return StoragePokemonDetails(
            health = pokemonDetails.health,
            urlAddress = pokemonDetails.urlAddress,
            attack = pokemonDetails.attack,
            experience = pokemonDetails.experience,
            name = pokemonDetails.name
        )

    }

    fun mapToDomain(entity: List<StorageNameAndUrl>): List<NameAndUrl> {
        val output: MutableList<NameAndUrl> = mutableListOf()
        for (i in entity.indices) {
            output.add(
                NameAndUrl(
                    name = entity[i].name,
                    urlAddress = entity[i].urlAddress
                )
            )
        }
        return output
    }

    fun mapToPokemon(pokemonApiDetails: PokemonDetailsById): StoragePokemonDetails {
        return StoragePokemonDetails(
            name = pokemonApiDetails.name,
            health = pokemonApiDetails.stats[HEALTH_ID].base_stat,
            experience = pokemonApiDetails.exp,
            attack = pokemonApiDetails.stats[ATTACK_ID].base_stat,
            urlAddress = pokemonApiDetails.sprite.front
        )
    }

}