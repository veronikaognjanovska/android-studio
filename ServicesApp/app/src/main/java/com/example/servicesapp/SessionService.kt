package com.example.servicesapp

import android.content.Context
import android.content.SharedPreferences

class SessionService {
    var preferences: SharedPreferences
    var editor: SharedPreferences.Editor
    var context: Context
    var PRIVATE_MODE: Int = 0

    companion object {
        val PRED_NAME = "MpipDemo"
    }

    constructor(context: Context) {
        this.context = context
        preferences = context.getSharedPreferences(PRED_NAME, PRIVATE_MODE)
        editor = preferences.edit()
    }

    fun saveData(key: String, value: String) {
        editor.putString(key, value)
        editor.apply()
    }

    fun getData(key: String?): String? {
        return preferences.getString(key, "")
    }
}