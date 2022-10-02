package com.ym.basic.tool

import android.app.Application
import android.os.Handler
import android.os.Looper
import android.widget.Toast

/**
 *@author: mao.ye
 *@createTime: 2022/10/2 11:23
 *@desc: 弹窗Toast工具类
 */
object TipUtil {
    private var context: Application? = null
    private var handler: Handler? = null

    /**
     * 需要调用此方法初始化，用于在子线程也能弹出toast
     */
    fun init(context: Application) {
        this.context = context
        handler = Handler(context.mainLooper)
    }


    /**
     * 显示时间短提示
     */
    fun <T> toast(value: T) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            toastReal(value, Toast.LENGTH_SHORT)
            return
        }
        handler?.post {
            toastReal(value, Toast.LENGTH_SHORT)
        }
    }

    /**
     * 显示长时间提示
     */
    fun toastLong(value: String) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            toastReal(value, Toast.LENGTH_LONG)
            return
        }
        handler?.post {
            toastReal(value, Toast.LENGTH_LONG)
        }
    }


    /**
     * @param type Toast.LENGTH_SHORT Toast.LENGTH_LONG
     */
    private fun <T> toastReal(value: T, type: Int) {
        when (value) {
            is Int -> {
                Toast.makeText(context, value, type).show()
            }
            is CharSequence -> {
                Toast.makeText(context, value, type).show()
            }
        }
    }
}