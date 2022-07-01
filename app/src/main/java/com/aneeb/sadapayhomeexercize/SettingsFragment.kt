package com.example.myapplication

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.aneeb.sadapayhomeexercize.R


class SettingsFragment : PreferenceFragmentCompat(),
    SharedPreferences.OnSharedPreferenceChangeListener {

    var darkModeString = ""
    var darkModeValues: Array<String> = arrayOf()

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.pref)
        PreferenceManager.getDefaultSharedPreferences(requireContext())
            .registerOnSharedPreferenceChangeListener(this)
        darkModeString = requireContext().getString(R.string.pref_key_dark_mode)
        darkModeValues = resources.getStringArray(R.array.pref_dark_mode_values)

    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        key?.let {
            if (it == darkModeString) sharedPreferences?.let { pref ->
                when (pref.getString(darkModeString, darkModeValues[0])) {
                    darkModeValues[0] -> {
                        Log.d("SettingsFragment : ", "==check ${darkModeValues[0]}")
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                    }
                    darkModeValues[1] -> {
                        Log.d("SettingsFragment : ", "==check ${darkModeValues[1]}")

                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    }
                    darkModeValues[2] -> {
                        Log.d("SettingsFragment : ", "==check ${darkModeValues[2]}")

                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    }
                    else -> {
                    }
                }
            }
        }
    }
}