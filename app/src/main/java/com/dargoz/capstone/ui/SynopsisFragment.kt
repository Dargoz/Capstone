package com.dargoz.capstone.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dargoz.capstone.databinding.SynopsisFragmentBinding
import com.dargoz.domain.models.Anime


class SynopsisFragment : Fragment() {

    companion object {
        fun newInstance() = SynopsisFragment()
    }

    private var _binding: SynopsisFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SynopsisFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val anime : Anime = requireParentFragment().requireArguments().getParcelable("anime")!!
        binding.animeSynopsis.text = anime.synopsis
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}