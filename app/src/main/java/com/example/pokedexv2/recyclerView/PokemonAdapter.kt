package com.example.pokedexv2.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedexv2.R
import com.example.pokedexv2.databinding.PokemonItemBinding


class PokemonAdapter(pokemonList: MutableList<String>, private val listener: PokemonListener) :
    RecyclerView.Adapter<PokemonAdapter.PokemonHolder>() {

    var pokemonListAdapter = pokemonList


    class PokemonHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = PokemonItemBinding.bind(item)

        fun bind(pokemon: String, listener: PokemonListener) {
            binding.apply {
                tvName.text = pokemon
//                Picasso.get()
//                    .load()
//                    .placeholder(R.drawable.pokepedia)
//                    .error(R.drawable.ic_launcher_background)
//                    .fit()
//                    .into(sprite)
                itemView.setOnClickListener {
                    listener.onPokemonClicked(pokemon = pokemon)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item, parent, false)
        return PokemonHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonHolder, position: Int) {
        holder.bind(pokemon = pokemonListAdapter[position], listener = listener)
    }

    override fun getItemCount(): Int {
        return pokemonListAdapter.size
    }


    fun update(newPokemonList: MutableList<String>) {
        pokemonListAdapter = newPokemonList
        notifyDataSetChanged()
    }


    interface PokemonListener {
        fun onPokemonClicked(pokemon: String)
    }
}