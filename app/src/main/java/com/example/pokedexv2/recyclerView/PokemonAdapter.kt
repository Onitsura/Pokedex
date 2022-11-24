package com.example.pokedexv2.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.PokemonDetails
import com.example.pokedexv2.R
import com.example.pokedexv2.databinding.PokemonItemBinding
import com.squareup.picasso.Picasso

class PokemonAdapter(private val pokemonList: ArrayList<PokemonDetails>, private val listener: PokemonListener) :
    RecyclerView.Adapter<PokemonAdapter.PokemonHolder>() {


    class PokemonHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = PokemonItemBinding.bind(item)

        fun bind(pokemon: PokemonDetails, listener: PokemonListener) {
            binding.apply {
                tvName.text = pokemon.name
                Picasso.get()
                    .load(pokemon.urlAddress)
                    .placeholder(R.drawable.pokepedia)
                    .error(R.drawable.ic_launcher_background)
                    .fit()
                    .into(sprite)
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
        holder.bind(pokemon = pokemonList[position], listener = listener)
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    fun addPokemon(pokemon: PokemonDetails) {
        pokemonList.add(pokemon)
        notifyDataSetChanged()
    }

    interface PokemonListener{
        fun onPokemonClicked(pokemon: PokemonDetails)
    }
}