package com.dargoz.capstone.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.dargoz.capstone.databinding.PersonalFragmentBinding
import com.dargoz.capstone.vm.PersonalViewModel

class PersonalFragment : Fragment() {

    companion object {
        fun newInstance() = PersonalFragment()
    }
    private var _binding: PersonalFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PersonalViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PersonalFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.favoriteCardView.setOnClickListener {
            try {
                moveToFavoriteActivity()
            } catch (e: Exception) {
                Toast.makeText(context, "module favorite not found", Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun moveToFavoriteActivity() {
        startActivity(Intent(activity, Class.forName("com.dargoz.favorite.FavoriteActivity")))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}