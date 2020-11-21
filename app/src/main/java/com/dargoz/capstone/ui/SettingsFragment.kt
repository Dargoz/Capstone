package com.dargoz.capstone.ui

import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.dargoz.capstone.R
import com.dargoz.capstone.utils.LocaleUtil

class SettingsFragment : PreferenceFragmentCompat(),
    SharedPreferences.OnSharedPreferenceChangeListener {
    companion object {
        const val KEY_PREF_LANGUAGE = "language"
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
        val languagePreference = findPreference<ListPreference>(KEY_PREF_LANGUAGE)
        languagePreference!!.summary = languagePreference.entry

    }

    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if(key == KEY_PREF_LANGUAGE) {
            val languagePreference = findPreference<ListPreference>(key)
            languagePreference!!.summary = languagePreference.entry
            LocaleUtil.setLocale(requireContext(),
                PreferenceManager
                    .getDefaultSharedPreferences(requireContext())
                    .getString(key, ""))
            requireActivity().recreate()
        }
    }


}