package com.example.pokedexv2.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.domain.models.PokemonDetails
import com.example.pokedexv2.R
import com.example.pokedexv2.databinding.ActivityMainBinding
import com.example.pokedexv2.recyclerView.PokemonAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), PokemonAdapter.PokemonListener {

        lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initFragment()

    }


    private fun initFragment(){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.frgm_cont, NamesListFragment.newInstance())
                .commit()
    }

    override fun onPokemonClicked(pokemon: PokemonDetails) {

    }
}