package com.ym.basic.tool

import android.util.Log

/**
 *@author: mao.ye
 *@createTime: 2022/9/25 20:08
 *@desc: 日志工具类
 */
object LogUtil {
    private var isDebug: Boolean = true
    fun setDebug(debug: Boolean) {
        this.isDebug = debug
    }

    fun d(tag: String, value: String) {
        if (isDebug) {
            Log.d(tag, value)
        }
    }

    fun i(tag: String, value: String) {
        if (isDebug) {
            Log.i(tag, value)
        }
    }

    fun w(tag: String, value: String) {
        if (isDebug) {
            Log.w(tag, value)
        }
    }

    fun e(tag: String, value: String) {
        if (isDebug) {
            Log.e(tag, value)
        }
    }
}