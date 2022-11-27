package com.example.pokedexv2.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokedexv2.databinding.FragmentNamesListBinding
import com.example.pokedexv2.recyclerView.PokemonAdapter
import java.util.*

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


    private fun init() {
        binding.apply {

            //Searchbar
            searchBar.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {}
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(
                    s: CharSequence?,
                    start: Int,
                    before: Int, count: Int
                ) {
                    search(searchBar, adapter)

                }
            })

            //RecyclerView
            rcView.layoutManager = GridLayoutManager(layoutInflater.context, 2)
            val namesList = mutableListOf<String>()
            adapter = PokemonAdapter(namesList, this@NamesListFragment)
            rcView.adapter = adapter
            viewModel.namesLiveData.observe(viewLifecycleOwner) { name ->
                namesList.add(name)
                adapter.update(namesList.sorted() as MutableList<String>)
            }

        }

    }

    companion object {
        fun newInstance() = NamesListFragment()
    }

    override fun onPokemonClicked(pokemon: String) {

    }


//Метод для поиска по списку
    fun search(view: EditText, adapter: PokemonAdapter) {
        val s = view.text
        viewModel.allNames.observe(viewLifecycleOwner) {
            if (s?.length == 0) {
                // Пользователь очистил поле поиска. Показываем все предметы
                adapter.pokemonListAdapter = it.sorted() as MutableList<String>
            } else {
                // Пользователь что-то ввёл. Делаем поиск по этому запросу
                adapter.pokemonListAdapter = it.filter {
                    it.contains(s.toString(), true)
                } as ArrayList
            }
            adapter.notifyDataSetChanged()
        }
    }

}


