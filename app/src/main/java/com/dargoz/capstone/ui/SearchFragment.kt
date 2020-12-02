package com.dargoz.capstone.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
        Log.i("DRG","onQueryTextSubmit : $query")
        viewModel.searchAnime(query!!).observe(viewLifecycleOwner, { anime ->
            when(anime) {
                is Resource.Loading -> Log.d("DRG", "Loading...")
                is Resource.Success -> {
                    Log.i("DRG", "Top Success : ${anime.data!!.size}")

                    adapter.setSearchList(anime.data!!)
                    adapter.notifyDataSetChanged()
                }
                is Resource.Error -> Log.w("DRG", "Error : ${anime.message}")
            }


        })
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