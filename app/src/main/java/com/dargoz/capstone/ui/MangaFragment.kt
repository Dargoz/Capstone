package com.dargoz.capstone.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dargoz.capstone.R
import com.dargoz.capstone.databinding.MangaFragmentBinding
import com.dargoz.capstone.ui.adapters.MangaListAdapter
import com.dargoz.capstone.vm.MangaViewModel
import com.dargoz.domain.Resource
import com.dargoz.domain.models.Manga
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MangaFragment : Fragment(), MangaListAdapter.OnClick {

    companion object {
        fun newInstance() = MangaFragment()
    }

    private var _binding: MangaFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MangaViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MangaFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        showTopMangaList()
        showTopManhwaList()
    }

    private fun showTopMangaList() {
        val adapter = MangaListAdapter()
        adapter.setListener(this)
        binding.topMangaRcView.adapter = adapter
        viewModel.topManga.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Loading -> {
                    //do nothing - already have shimmer forr screen loading
                }
                is Resource.Success -> {
                    val mangaList = it.data
                    binding.topMangaLoadingLayout.root.stopShimmer()
                    binding.topMangaLoadingLayout.root.visibility = View.GONE
                    adapter.setMangaList(mangaList!!)
                    adapter.notifyDataSetChanged()

                }
                is Resource.Error ->
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.resource_error_text), Toast.LENGTH_LONG
                    ).show()
            }
        })
    }

    private fun showTopManhwaList() {
        val adapter = MangaListAdapter()
        adapter.setListener(this)
        binding.topManhwaRcView.adapter = adapter
        viewModel.topManhwa.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Loading -> {
                    //do nothing - already have shimmer forr screen loading
                }
                is Resource.Success -> {
                    val mangaList = it.data
                    binding.topManhwaLoadingLayout.root.stopShimmer()
                    binding.topManhwaLoadingLayout.root.visibility = View.GONE
                    if (mangaList != null && mangaList.isNotEmpty()) {
                        adapter.setMangaList(mangaList)
                        adapter.notifyDataSetChanged()
                    }
                }
                is Resource.Error ->
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.resource_error_text), Toast.LENGTH_LONG
                    ).show()
            }
        })
    }

    override fun onItemClick(manga: Manga) {
        Toast.makeText(requireContext(),
            "Detail Manga Page Coming Soon!", Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}