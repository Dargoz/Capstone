package com.dargoz.capstone

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
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
        setContentView(binding.root)
        setViewFunctionality()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Log.w("DRG","configuration changed : ")
    }

    override fun applyOverrideConfiguration(overrideConfiguration: Configuration?) {
        Log.i("DRG","applyOverrideConfiguration : ")
        super.applyOverrideConfiguration(overrideConfiguration)
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