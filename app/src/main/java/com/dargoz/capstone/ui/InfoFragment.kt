package com.dargoz.capstone.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.dargoz.capstone.R

import com.dargoz.capstone.databinding.InfoFragmentBinding
import com.dargoz.capstone.utils.ActivityHelper
import com.dargoz.capstone.vm.InfoViewModel
import com.dargoz.domain.Resource
import com.dargoz.domain.models.Anime
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InfoFragment : Fragment() {

    companion object {
        fun newInstance() = InfoFragment()
    }
    private var _binding : InfoFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: InfoViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = InfoFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val anime: Anime = ActivityHelper.getAnimeResource(this)
        viewModel.animeData(anime.malId).observe(viewLifecycleOwner, { animeData ->
            if(animeData != null) {
                when(animeData) {
                    is Resource.Loading -> Log.i("DRG","Loading..")
                    is Resource.Success -> {
                        Log.d("DRG", "data ${animeData.data!!}")
                        showAnimeInformation(animeData.data!!)

                    }
                    is Resource.Error ->
                        Toast.makeText(requireContext(),
                            getString(R.string.resource_error_text), Toast.LENGTH_LONG).show()
                }
            }

        })
    }

    private fun showAnimeInformation(anime: Anime) {
        (requireParentFragment() as DetailFragment).updatePopularityData(anime.popularity)
        binding.infoJapaneseTitleValue.text = anime.titleJapanese
        binding.infoTypeValue.text = anime.type
        binding.infoEpisodesValue.text = anime.episodes.toString()
        binding.infoStatusValue.text = anime.status
        binding.infoDurationValue.text = anime.duration
        binding.infoRatingValue.text = anime.rating
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}