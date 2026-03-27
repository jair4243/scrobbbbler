package com.jair.scrobbbbler

import android.content.Context

object NotificationStore {
    private const val PREFS_NAME = "scrobbbbler_prefs"
    private const val KEY_LATEST_ENTRY = "latest_entry"

    fun saveLatestEntry(context: Context, entry: String) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(KEY_LATEST_ENTRY, entry).apply()
    }

    fun getLatestEntry(context: Context): String? {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getString(KEY_LATEST_ENTRY, null)
    }
}