package com.dargoz.capstone

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.dargoz.capstone.databinding.ActivityMainBinding
import com.dargoz.capstone.utils.LocaleUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(LocaleUtil.onAttach(newBase!!))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setActionBar()
        setContentView(binding.root)
        setViewFunctionality()
    }

    private fun setActionBar() {
        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setCustomView(R.layout.action_bar_layout)
        supportActionBar!!.elevation = 0.0F
    }

    private fun setViewFunctionality() {
        val navHostFragment: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        NavigationUI.setupWithNavController(
            binding.mainBottomNavigation,
            navHostFragment.navController
        )
    }

    fun hideBottomNav(flag: Boolean) {
        binding.mainBottomNavigation.visibility = if (flag) View.GONE else View.VISIBLE
    }


}