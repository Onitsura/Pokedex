package com.example.pokedexv2.presentation

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.pokedexv2.R
import com.example.pokedexv2.databinding.FragmentNamesListBinding

class NamesListFragment : Fragment() {

    companion object {
        fun newInstance() = NamesListFragment()
    }

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
    }




}