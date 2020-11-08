package com.dargoz.capstone.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.fragment.app.viewModels
import com.dargoz.capstone.R
import com.dargoz.capstone.databinding.DetailFragmentBinding
import com.dargoz.capstone.ui.adapters.ViewPagerAdapter
import com.dargoz.capstone.vm.DetailViewModel
import com.dargoz.domain.models.Anime
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private var _binding : DetailFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val anime: Anime = requireArguments().getParcelable("anime")!!
        showDetailAnimeData(anime)
    }

    private fun showDetailAnimeData(anime: Anime) {
        binding.animeTitle.text = anime.title
        binding.animeScore.text = anime.score.toString()
        setFavoriteState(anime)
        setupViewPager()

    }

    private fun setFavoriteState(anime: Anime) {
        binding.favoriteButton.imageTintList =
            AppCompatResources.getColorStateList(
                requireContext(),
                R.color.favorite_color_state_list
            )
        binding.favoriteButton.isActivated = anime.isFavorite
        binding.favoriteButton.setOnClickListener {
            val isFavorite = !anime.isFavorite
            viewModel.updateAnimeFavoriteFlag(anime.malId, isFavorite)
            binding.favoriteButton.isActivated = isFavorite
        }
    }

    private fun setupViewPager() {
        val adapter = ViewPagerAdapter(requireContext(), childFragmentManager,
            FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        binding.contentViewPager.adapter = adapter
        binding.contentTabLayout.setupWithViewPager(binding.contentViewPager)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}