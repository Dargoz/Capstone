package com.dargoz.capstone.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dargoz.capstone.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFavoriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_favorite)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, DetailFragment.newInstance())
                .commitNow()
        }
    }
}