package com.ym.basic.application

import android.app.Application

/**
 *@author: mao.ye
 *@createTime: 2022/9/25 16:33
 *@desc: 子模块的观察application的基类
 */
open class BaseAppLogic {
    private lateinit var application: Application
    open fun onCreate(application: Application) {
        this.application = application
    }

    open fun onTerminate() {
    }

    open fun onTrimMemory(level: Int) {
    }

    open fun onLowMemory() {

    }
}