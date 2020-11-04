package com.dargoz.capstone.ui.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.dargoz.capstone.R
import com.dargoz.capstone.ui.CharactersFragment
import com.dargoz.capstone.ui.InfoFragment
import com.dargoz.capstone.ui.ReviewsFragment
import com.dargoz.capstone.ui.SynopsisFragment
import java.lang.IllegalArgumentException

class ViewPagerAdapter constructor(val context: Context,
                                   fragmentManager: FragmentManager, behavior: Int)
    : FragmentStatePagerAdapter(fragmentManager, behavior) {
    private val synopsisFragment = SynopsisFragment.newInstance()
    private val infoFragment = InfoFragment.newInstance()
    private val charactersFragment = CharactersFragment.newInstance()
    private val reviewsFragment = ReviewsFragment.newInstance()


    override fun getCount(): Int = 4

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> synopsisFragment
            1 -> infoFragment
            2 -> charactersFragment
            3 -> reviewsFragment
            else -> throw IllegalArgumentException("unknown position")
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            0 -> context.resources.getString(R.string.synopsis_text)
            1 -> context.resources.getString(R.string.info_text)
            2 -> context.resources.getString(R.string.characters_text)
            3 -> context.resources.getString(R.string.reviews_text)
            else -> throw IllegalArgumentException("unknown position")
        }
    }

}