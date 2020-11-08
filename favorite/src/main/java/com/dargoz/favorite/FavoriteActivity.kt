package com.dargoz.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dargoz.favorite.ui.FavoriteFragment


class FavoriteActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.favorite_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, FavoriteFragment.newInstance())
                .commitNow()
        }
    }
}