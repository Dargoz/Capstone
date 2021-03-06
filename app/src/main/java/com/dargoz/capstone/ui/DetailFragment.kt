package com.dargoz.capstone.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.fragment.app.viewModels
import com.dargoz.capstone.MainActivity
import com.dargoz.capstone.R
import com.dargoz.capstone.databinding.DetailFragmentBinding
import com.dargoz.capstone.ui.adapters.ViewPagerAdapter
import com.dargoz.capstone.vm.DetailViewModel
import com.dargoz.domain.models.Anime
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    companion object {
        const val ANIME_DATA = "anime"
        fun newInstance(): DetailFragment {
            return DetailFragment()
        }
    }


    private var _binding: DetailFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireActivity()
        val anime: Anime = if (activity !is DetailFavoriteActivity) {
            (activity as MainActivity).hideBottomNav(true)
            requireArguments().getParcelable(ANIME_DATA)!!
        } else {
            activity.intent.getParcelableExtra(ANIME_DATA)!!
        }

        showDetailAnimeData(anime)
    }

    private fun showDetailAnimeData(anime: Anime) {
        Picasso.get().load(anime.imageUrl).into(binding.animePoster)
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
        val adapter = ViewPagerAdapter(
            requireContext(), childFragmentManager,
            FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )

        binding.contentViewPager.offscreenPageLimit = 4
        binding.contentViewPager.adapter = adapter
        binding.contentTabLayout.setupWithViewPager(binding.contentViewPager)
    }

    fun updatePopularityData(popularity: Long?) {
        binding.animePopularity.text = popularity.toString()
    }

    override fun onDestroyView() {
        if (requireActivity() is MainActivity) {
            (requireActivity() as MainActivity).hideBottomNav(false)
        }
        super.onDestroyView()
        _binding = null
    }

}