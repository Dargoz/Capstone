package com.dargoz.capstone.utils

import android.annotation.TargetApi
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import androidx.preference.PreferenceManager
import com.dargoz.capstone.ui.SettingsFragment
import java.util.*


class LocaleUtil {

    companion object {

        fun onAttach(context: Context): Context? {
            val locale = getPersistedLocale(context)
            return setLocale(context, locale)
        }

        fun getPersistedLocale(context: Context?): String? {
            val preferences: SharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getString(SettingsFragment.KEY_PREF_LANGUAGE, "")
        }

        /**
         * Set the app's locale to the one specified by the given String.
         *
         * @param context
         * @param localeSpec a locale specification as used for Android resources (NOTE: does not
         * support country and variant codes so far); the special string "system" sets
         * the locale to the locale specified in system settings
         * @return
         */
        @Suppress("DEPRECATION")
        fun setLocale(context: Context, localeSpec: String?): Context? {
            val locale: Locale = if (localeSpec == "system") {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    Resources.getSystem().configuration.locales.get(0)
                } else {
                    Resources.getSystem().configuration.locale
                }
            } else {
                Locale(localeSpec!!)
            }
            Locale.setDefault(locale)
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                updateResources(context, locale)
            } else {
                updateResourcesLegacy(context, locale)
            }
        }

        @TargetApi(Build.VERSION_CODES.N)
        private fun updateResources(context: Context, locale: Locale): Context? {
            val configuration: Configuration = context.resources.configuration
            configuration.setLocale(locale)
            configuration.setLayoutDirection(locale)
            return context.createConfigurationContext(configuration)
        }

        @Suppress("DEPRECATION")
        private fun updateResourcesLegacy(context: Context, locale: Locale): Context? {
            val resources: Resources = context.resources
            val configuration: Configuration = resources.configuration
            configuration.locale = locale
            configuration.setLayoutDirection(locale)
            resources.updateConfiguration(configuration, resources.displayMetrics)
            return context
        }
    }
}