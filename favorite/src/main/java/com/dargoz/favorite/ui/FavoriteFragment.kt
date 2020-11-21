package com.dargoz.favorite.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dargoz.capstone.di.FavoriteModuleDependencies
import com.dargoz.capstone.ui.adapters.AnimeListAdapter
import com.dargoz.favorite.databinding.FavoriteFragmentBinding
import com.dargoz.favorite.di.DaggerFavoriteComponent
import com.dargoz.favorite.di.withFactory
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteFragment : Fragment() {

    companion object {
        fun newInstance() = FavoriteFragment()
    }
    private var _binding: FavoriteFragmentBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var favoriteViewModelFactory: FavoriteViewModelFactory


    private val viewModel: FavoriteViewModel by viewModels{ withFactory(favoriteViewModelFactory) }

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
    }

    private fun inject() {
        DaggerFavoriteComponent.factory()
            .create(
                EntryPointAccessors.fromApplication(
                    requireActivity().applicationContext,
                    FavoriteModuleDependencies::class.java
                ),
                this)
            .inject(this)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FavoriteFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val adapter = AnimeListAdapter()
        binding.favoriteListRcView.adapter = adapter
        viewModel.favoriteAnimeList.observe(viewLifecycleOwner, {
            Log.i("DRG","favorite list : $it")
            if(it.isEmpty()) {
                binding.emptyFavoriteTextView.visibility = View.VISIBLE
            } else {
                binding.emptyFavoriteTextView.visibility = View.GONE
                adapter.setAnimeList(it)
                adapter.notifyDataSetChanged()
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}