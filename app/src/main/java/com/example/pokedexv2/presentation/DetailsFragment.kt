package com.example.pokedexv2.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.pokedexv2.R
import com.example.pokedexv2.databinding.FragmentDetailsBinding
import com.squareup.picasso.Picasso

private const val HEALTH = "Health: "
private const val ATTACK = "Attack: "
private const val EXPERIENCE = "Exp: "


class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: DetailsViewModel by activityViewModels()


    companion object {
        fun newInstance() = DetailsFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parentFragmentManager.setFragmentResultListener(
            "pokemonId", viewLifecycleOwner
        ) { pokemon, bundle ->
            viewModel.id.value = bundle.getLong("bundle")
            viewModel.load()
        }
        init()


    }

    private fun init() {
        binding.apply {
            viewModel.attack.observe(viewLifecycleOwner) {
                val text = ATTACK + it
                attackDetails.text = text
            }
            viewModel.experience.observe(viewLifecycleOwner) {
                val text = EXPERIENCE + it
                experienceDetails.text = text
            }
            viewModel.health.observe(viewLifecycleOwner) {
                val text = HEALTH + it
                healthDetails.text = text
            }
            viewModel.urlAddress.observe(viewLifecycleOwner) {
                Picasso.get().load(it).placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_launcher_background).fit().into(spriteDetails)
            }
            viewModel.name.observe(viewLifecycleOwner) {
                nameDetails.text = it
            }
            backBtn.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.frgm_cont, NamesListFragment.newInstance()).commit()
            }
        }
    }
}