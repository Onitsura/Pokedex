package com.example.pokedexv2.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.pokedexv2.R
import com.example.pokedexv2.databinding.ActivityMainBinding
import com.example.pokedexv2.recyclerView.PokemonAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

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

    private fun initDetailsFragment(){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frgm_cont, DetailsFragment.newInstance())
            .commit()
    }


}