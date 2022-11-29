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
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokedexv2.R
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
        binding.apply {
            Log.e("Frgm", viewModel.allNames.value.toString())
            if (viewModel.allNames.value!!.isNotEmpty()) {
                rcView.layoutManager = GridLayoutManager(layoutInflater.context, 2)
                val namesList = viewModel.allNames.value!!
                adapter = PokemonAdapter(namesList, this@NamesListFragment)
                adapter.update(namesList.sorted() as MutableList<String>)
                rcView.adapter = adapter

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

            }
        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (viewModel.allNames.value?.isEmpty()!!) {
            init()
        }
    }


    private fun init() {
        Log.e("Frgm", "Recreate")
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
                namesList.add(name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() })
                adapter.update(namesList.sorted() as MutableList<String>)

            }


        }

    }

    companion object {
        fun newInstance() = NamesListFragment()
    }

    override fun onPokemonClicked(pokemon: String) {

        binding.apply {
            Log.e("trans1", pokemon)

            viewModel.nameClicked.value = pokemon



            setFragmentResult("pokemon", bundleOf("bundle" to pokemon.replaceFirstChar {
                it.lowercase(
                    Locale.getDefault()
                )
            }))

            parentFragmentManager.beginTransaction()
                .replace(R.id.frgm_cont, DetailsFragment.newInstance())
                .addToBackStack("Names")
                .commit()
        }
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


//    private fun loadImage(urlAddress: String) {
//
//            Picasso.get()
//                .load(urlAddress)
//                .placeholder(R.drawable.ic_launcher_foreground)
//                .error(R.drawable.ic_launcher_background)
//                .fit()
//                .into()
//        }

}





