package com.example.pokedexv2.data.retrofit

import android.util.Log
import com.example.pokedexv2.data.retrofit.models.PokemonDetailsById
import com.example.pokedexv2.data.retrofit.models.PokemonEntries
import com.example.pokedexv2.data.retrofit.models.PokemonNameAndId
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

class RemoteDataSource @Inject constructor(val apiService: ApiService) {

    private val idAll: MutableList<Long> = mutableListOf()



    suspend fun loadNames(apiService: ApiService): MutableList<String> {
        val names: MutableList<String> = mutableListOf()
        val receive: List<PokemonEntries> = (apiService.getNames().pokemon_entries)
        receive.map {
                names.add(it.species.name)
                idAll.add(it.entry_number)
        }
        return names
    }


  //    Метод для загрузки всех покемонов
//    private suspend fun loadInfo(apiService: ApiService): HashMap<Long, StoragePokemonDetails> {
//        val pokemonsList: HashMap<Long, StoragePokemonDetails> = hashMapOf()
////        for (id in allId) {
//
//
//            val detailInfo = apiService.getDetailsById(id.toString())
//            detailInfo.enqueue(object : Callback<PokemonDetailsById> {
//                override fun onResponse(
//                    call: Call<PokemonDetailsById>,
//                    response: Response<PokemonDetailsById>
//                ) {
//                    val pokemon = StoragePokemonDetails(
//                        id = id,
//                        urlAddress = response.body()?.sprite?.front.toString(),
//                        health = response.body()?.stats?.get(0)?.base_stat.toString(),
//                        attack = response.body()?.stats?.get(1)?.base_stat.toString(),
//                        experience = response.body()?.exp.toString(),
//                        name = response.body()?.name.toString()
//                            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
//                    )
//                    pokemonsList[id] = pokemon
//                }
//
//                override fun onFailure(call: Call<PokemonDetailsById>, t: Throwable) {
//                }
//
//            })
//        }
//        return pokemonsList
//   }

}




