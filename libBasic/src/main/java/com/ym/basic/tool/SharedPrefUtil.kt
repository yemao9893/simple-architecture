package com.ym.basic.tool

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

/**
 *@author: mao.ye
 *@createTime: 2022/10/2 14:12
 *@desc: null
 */
object SharedPrefUtil {
    //    var sharedPreferences:SharedPreferences?=null
    private var context: Application? = null
    private var name: String = "SharedPref"
    fun init(context: Application, name: String = "SharedPref") {
        this.context = context
        this.name = name
    }

    private fun getSharedPreferences(): SharedPreferences? {
        return context?.getSharedPreferences(name, Context.MODE_PRIVATE)
    }

    fun put(key: String, value: Int) {
        putReal(key, value)
    }

    fun put(key: String, value: String) {
        putReal(key, value)
    }

    fun putBoolean(key: String, value: Boolean) {
        putReal(key, value)
    }

    fun putLong(key: String, value: Long) {
        putReal(key, value)
    }

    private fun <T> putReal(key: String, value: T) {
        val editor = getSharedPreferences()?.edit() ?: return
        when (value) {
            is Int -> {
                editor.putInt(key, value)
            }
            is String -> {
                editor.putString(key, value)
            }
            is Boolean -> {
                editor.putBoolean(key, value)
            }
            is Long -> {
                editor.putLong(key, value)
            }
        }
        editor.apply()
    }
}