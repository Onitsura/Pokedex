package com.example.pokedexv2.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokedexv2.databinding.FragmentNamesListBinding
import com.example.pokedexv2.recyclerView.PokemonAdapter

class NamesListFragment : Fragment(), PokemonAdapter.PokemonListener {



    private lateinit var adapter: PokemonAdapter
    private lateinit var binding: FragmentNamesListBinding
    private val viewModel: NamesListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNamesListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }



    private fun init(){
//TODO сделать нормальную загрузку данных, убрать дефолтное значение
        val defaultPoke = ""
        val list2 = mutableListOf(defaultPoke)

        binding.apply {
            rcView.layoutManager = GridLayoutManager(layoutInflater.context, 2)
            val list: MutableLiveData<MutableList<String>> = MutableLiveData(list2)
            viewModel.liveData.observe(viewLifecycleOwner) {
                list.add(it)
                adapter.update(list.value?: list2)
                Log.e("Flow", "list size = ${list.value?.size}")
            }
            adapter = PokemonAdapter(list.value?: list2, this@NamesListFragment)
            rcView.adapter = adapter

        }
    }

    companion object {
        fun newInstance() = NamesListFragment()
    }

    override fun onPokemonClicked(pokemon: String) {

    }

}

private fun <T> MutableLiveData<MutableList<T>>.add(it: T) {
    val updatedItems = this.value as ArrayList
    updatedItems.add(it)
    this.value = updatedItems
}
