package com.dargoz.capstone.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.dargoz.capstone.R
import com.dargoz.capstone.databinding.SearchFragmentBinding
import com.dargoz.capstone.ui.DetailFragment.Companion.ANIME_DATA
import com.dargoz.capstone.ui.adapters.SearchListAdapter
import com.dargoz.capstone.vm.SearchViewModel
import com.dargoz.domain.Resource
import com.dargoz.domain.models.Anime
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(), SearchView.OnQueryTextListener, SearchListAdapter.OnClick {

    companion object {
        fun newInstance() = SearchFragment()
    }
    private var _binding: SearchFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchViewModel by viewModels()
    private lateinit var navController: NavController
    private lateinit var adapter: SearchListAdapter
    private var firstSubmit = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SearchFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = SearchListAdapter()
        adapter.setListener(this)
        binding.searchRcView.adapter = adapter
        binding.searchView.setOnQueryTextListener(this)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onQueryTextSubmit(query: String?): Boolean {

        val result = viewModel.searchAnime(query!!)
        if(firstSubmit) {
            firstSubmit = false
            result.observe(viewLifecycleOwner, { anime ->
                when(anime) {
                    is Resource.Loading -> {
                        binding.searchErrorText.visibility = View.GONE
                        binding.searchLottieAnimation.visibility = View.GONE
                        Toast.makeText(
                            requireContext(),
                            getString(R.string.loading_text),
                            Toast.LENGTH_LONG).show()
                    }
                    is Resource.Success -> {
                        adapter.setSearchList(anime.data!!)
                        adapter.notifyDataSetChanged()
                    }
                    is Resource.Error -> {
                        binding.searchErrorText.visibility = View.VISIBLE
                        binding.searchLottieAnimation.visibility = View.VISIBLE
                    }
                }


            })
        }

        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        //do nothing
        return true
    }

    override fun onItemClick(anime: Anime) {
        val bundle = Bundle()
        bundle.putParcelable(ANIME_DATA, anime)
        navController.navigate(R.id.action_searchFragment_to_detailFragment, bundle)
    }

}