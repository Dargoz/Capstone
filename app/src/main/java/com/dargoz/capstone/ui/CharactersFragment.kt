package com.dargoz.capstone.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dargoz.capstone.databinding.CharactersFragmentBinding
import com.dargoz.capstone.ui.adapters.CharacterStaffListAdapter
import com.dargoz.capstone.vm.CharactersViewModel
import com.dargoz.domain.Resource
import com.dargoz.domain.models.Anime
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : Fragment() {

    companion object {
        fun newInstance() = CharactersFragment()
    }

    private var _binding: CharactersFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CharactersViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CharactersFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val adapter = CharacterStaffListAdapter()
        binding.animeCharacterRcView.adapter = adapter
        val anime : Anime = requireParentFragment().requireArguments().getParcelable("anime")!!
        viewModel.animeCharactersAndStaff(anime.malId).observe(viewLifecycleOwner, { characters ->
            when(characters) {
                is Resource.Loading -> Log.i("DRG","Loading Characters...")
                is Resource.Success -> {
                    Log.v("DRG","characters list : ${characters.data}")
                    if(characters.data != null) {
                        adapter.characterStaffList = characters.data!!
                        adapter.notifyDataSetChanged()
                    }
                }
                is Resource.Error -> {
                    Log.v("DRG","error : ${characters.data}")
                }
            }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}