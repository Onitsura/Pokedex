package com.example.pokedexv2.data.retrofit

import android.util.Log
import com.example.pokedexv2.data.retrofit.models.PokemonNameAndId
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(apiService: ApiService) {

    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.Unconfined + job)
    val names: MutableList<String> = mutableListOf()

    //TODO Поробовать обернуть создание флоу в лаунч и запускать флоу внутри лаунча с запросом в сеть??
    val pokemonNames: Deferred<Flow<String>> = scope.async {
        Log.e("Flow", "Load data has been started")
        loadData(apiService, names)
        Log.e("Flow", "Flow has been started")
        flow {

            delay(1_000)
            emitAll(names.asFlow())
            Log.e("Flow", "Flow is arrive")
        }
            .flowOn(Dispatchers.IO)
    }


    private fun getFlow(): Deferred<Flow<String>> = scope.async {

        flow {

            delay(1_000)
            emitAll(names.asFlow())
            Log.e("Flow", "Flow is arrive")
        }
            .flowOn(Dispatchers.IO)


    }


    private suspend fun doWork(apiService: ApiService, names: MutableList<String>): List<String> =
        scope.async {

            val pokemonDetails = apiService.getNames()
            pokemonDetails.enqueue(object : Callback<PokemonNameAndId> {
                override fun onResponse(
                    call: Call<PokemonNameAndId>,
                    response: Response<PokemonNameAndId>
                ) {
                    val allPoke = ArrayList(response.body()?.pokemon_entries!!)
                    for (i in 0 until allPoke.size) {
                        names.add(allPoke[i].species.name)
                        names.sorted()
                    }
                    Log.e("Flow", "names uploaded1")

                }


                override fun onFailure(call: Call<PokemonNameAndId>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
            return@async names.sorted()
        }.await()

    private suspend fun loadData(apiService: ApiService, names: MutableList<String>): Job =
        scope.launch {
            try {
                doWork(apiService = apiService, names = names)
            } catch (e: Exception) {

            }
        }


    private suspend fun doWork2(apiService: ApiService, names: MutableList<String>): Job =
        scope.launch {

            val pokemonDetails = apiService.getNames()
            pokemonDetails.enqueue(object : Callback<PokemonNameAndId> {
                override fun onResponse(
                    call: Call<PokemonNameAndId>,
                    response: Response<PokemonNameAndId>
                ) {

                    val allPoke = ArrayList(response.body()?.pokemon_entries!!)
                    for (i in 0 until allPoke.size) {
                        names.add(allPoke[i].species.name)
                        names.sorted()
                    }
                    Log.e("Flow", "names uploaded2")


                }


                override fun onFailure(call: Call<PokemonNameAndId>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
            names.sorted()
        }


//    private suspend fun getNames(apiService: ApiService): List<String> = coroutineScope {
//        val names = mutableListOf<String>()
//        val pokemonDetails = apiService.getNames()
//
//
//        val sortedNames: Job = launch(Dispatchers.IO) {
//
//            pokemonDetails.enqueue(object : Callback<PokemonNameAndId>{
//                override fun onResponse(
//                    call: Call<PokemonNameAndId>,
//                    response: Response<PokemonNameAndId>
//                ) {
//                    val allPoke = ArrayList(response.body()?.pokemon_entries!!)
//                    for (i in 0 until allPoke.size) {
//                        names.add(allPoke[i].species.name)
//                        names.sorted()
//                    }
//                    Log.e("Flow", "names uploaded")
//
//                }
//
//
//                override fun onFailure(call: Call<PokemonNameAndId>, t: Throwable) {
//                    TODO("Not yet implemented")
//                }
//            })
//
//
//        }
//
//        sortedNames.join()
//
//
//
//        Log.e("Flow", names.size.toString())
//        return@coroutineScope names
//
//
//
//
//
//
//
//    }

//    val names: Deferred<List<String>> = GlobalScope.async {
//        val names = mutableListOf<String>()
//        val pokemonDetails = apiService.getNames()
//        pokemonDetails.enqueue(object : Callback<PokemonNameAndId>{
//            override fun onResponse(
//                call: Call<PokemonNameAndId>,
//                response: Response<PokemonNameAndId>
//            ) {
//                val allPoke = ArrayList(response.body()?.pokemon_entries!!)
//                for (i in 0 until allPoke.size) {
//                    names.add(allPoke[i].species.name)
//                    names.sorted()
//                }
//                Log.e("Flow", "names uploaded")
//
//            }
//
//
//            override fun onFailure(call: Call<PokemonNameAndId>, t: Throwable) {
//                TODO("Not yet implemented")
//            }
//        })
//        return@async names.sorted()
//    }


}