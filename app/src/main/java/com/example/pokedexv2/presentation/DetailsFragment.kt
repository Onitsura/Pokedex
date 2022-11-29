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

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: DetailsViewModel by activityViewModels()


    companion object {
        fun newInstance() = DetailsFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parentFragmentManager.setFragmentResultListener(
            "pokemon",
            viewLifecycleOwner
        ) { pokemon, bundle ->
            viewModel.name.value = bundle.getString("bundle").toString()
            Log.e("trans2", viewModel.name.value.toString())
            viewModel.load()
        }
        init()


    }

    private fun init() {
        binding.apply {
            viewModel.attack.observe(viewLifecycleOwner) {
                Log.e("otdelno", it)
                attackDetails.text = it
            }
            viewModel.experience.observe(viewLifecycleOwner) {
                Log.e("otdelno", it)
                experienceDetails.text = it
            }
            viewModel.health.observe(viewLifecycleOwner) {
                Log.e("otdelno", it)
                healthDetails.text = it
            }
            viewModel.urlAddress.observe(viewLifecycleOwner) {
                Log.e("otdelno", it)
                Picasso.get()
                    .load(it)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_launcher_background)
                    .fit()
                    .into(spriteDetails)
            }

            backBtn.setOnClickListener {
//                FragmentManager fragmentManager = getFragmentManager()
//                fragmentManager.beginTransaction()
//                    .remove(fragment1)
//                    .add(R.id.fragment_container, fragment2)
//                    .show(fragment3)
//                    .hide(fragment4)
//                    .commit();


                val manager = requireActivity().supportFragmentManager
                manager.popBackStack()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.frgm_cont, NamesListFragment.newInstance())
                    .commit()
            }
        }
    }


}