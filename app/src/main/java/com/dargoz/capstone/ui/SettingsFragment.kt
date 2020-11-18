package com.dargoz.capstone.ui

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.dargoz.capstone.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }

}