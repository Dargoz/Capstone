package com.dargoz.capstone.utils

import androidx.fragment.app.Fragment
import com.dargoz.capstone.MainActivity
import com.dargoz.capstone.ui.DetailFavoriteActivity
import com.dargoz.capstone.ui.DetailFragment
import com.dargoz.domain.models.Anime

class ActivityHelper private constructor() {
    companion object {
        fun getAnimeResource(fragment: Fragment): Anime {
            val activity = fragment.requireActivity()
            return if (activity !is DetailFavoriteActivity) {
                (activity as MainActivity).hideBottomNav(true)
                fragment.requireParentFragment().requireArguments()
                    .getParcelable(DetailFragment.ANIME_DATA)!!
            } else {
                activity.intent.getParcelableExtra(DetailFragment.ANIME_DATA)!!
            }
        }

    }
}