package com.chocolate_team.todo_application.util

import android.content.Context
import android.content.SharedPreferences

object PrefsUtil {
    private var sharedPreferences:SharedPreferences?=null
    private const val SHARED_PREFS_NAME ="my shared"
    private const val KEY_MODE="KEY_MODE"
    fun initialPrefs(context: Context){
        sharedPreferences= context.getSharedPreferences(SHARED_PREFS_NAME,Context.MODE_PRIVATE)
    }
    var modeLight :Boolean?
    get()= sharedPreferences?.getBoolean(KEY_MODE,true)
        set(value) {
            sharedPreferences?.edit()?.putBoolean(KEY_MODE,value!!)?.apply()
        }

}