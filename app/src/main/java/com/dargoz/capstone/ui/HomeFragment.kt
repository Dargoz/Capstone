package com.dargoz.capstone.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.dargoz.capstone.R
import com.dargoz.capstone.databinding.HomeFragmentBinding
import com.dargoz.capstone.ui.adapters.AnimeListAdapter
import com.dargoz.capstone.vm.HomeViewModel
import com.dargoz.domain.Resource
import com.dargoz.domain.models.Anime
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment(), AnimeListAdapter.OnClick {
    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        showCurrentSeasonAnimeList()
        showTopUpcomingAnimeList()
        showTodayAnimeList()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    private fun showTopUpcomingAnimeList() {
        val adapter = AnimeListAdapter()
        adapter.setOnItemClickListener(this)
        binding.animeSuggestionRcView.adapter = adapter
        viewModel.topUpcomingAnime.observe(viewLifecycleOwner, { anime ->
            if (anime != null) {
                when (anime) {
                    is Resource.Loading -> Log.d("DRG", "Loading...")
                    is Resource.Success -> {
                        Log.i("DRG", "Top Success : ${anime.data!!.size}")
                        binding.animeSuggestionLoadingLayout .root.stopShimmer()
                        binding.animeSuggestionLoadingLayout.root.visibility = View.GONE
                        adapter.setAnimeList(anime.data!!)
                        adapter.notifyDataSetChanged()
                    }
                    is Resource.Error -> Log.w("DRG", "Error : ${anime.message}")
                }
            }

        })
    }

    private fun showCurrentSeasonAnimeList() {
        val adapter = AnimeListAdapter()
        adapter.setOnItemClickListener(this)
        binding.currentSeasonTitle.text = viewModel.setCurrentSeasonTitle()
        binding.currentSeasonRcView.adapter = adapter
        viewModel.anime.observe(viewLifecycleOwner, { anime ->
            if (anime != null) {
                when (anime) {
                    is Resource.Loading -> Log.d("DRG", "Loading...")
                    is Resource.Success -> {
                        Log.i("DRG", "Success : ${anime.data!!.size}")
                        binding.currentSeasonLoadingLayout.root.stopShimmer()
                        binding.currentSeasonLoadingLayout.root.visibility = View.GONE
                        adapter.setAnimeList(anime.data!!)
                        adapter.notifyDataSetChanged()
                    }
                    is Resource.Error -> Log.w("DRG", "Error : ${anime.message}")
                }
            }
        })
    }

    private fun showTodayAnimeList() {
        val adapter = AnimeListAdapter()
        adapter.setOnItemClickListener(this)
        binding.todayEpisodeRcView.adapter = adapter
        viewModel.todayAnimeSchedule.observe(viewLifecycleOwner, { anime ->
            if (anime != null) {
                when (anime) {
                    is Resource.Loading -> Log.d("DRG", "Loading...")
                    is Resource.Success -> {
                        Log.i("DRG", "Today Success : ${anime.data!!.size}")
                        binding.todayEpisodeLoadingLayout .root.stopShimmer()
                        binding.todayEpisodeLoadingLayout.root.visibility = View.GONE
                        adapter.setAnimeList(anime.data!!)
                        adapter.notifyDataSetChanged()
                    }
                    is Resource.Error -> Log.w("DRG", "Error : ${anime.message}")
                }
            }

        })
    }

    override fun onItemClick(anime: Anime) {
        val bundle = Bundle()
        bundle.putParcelable("anime", anime)
        navController.navigate(R.id.action_homeFragment_to_detailFragment, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}