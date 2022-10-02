package com.ym.basic.application

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication

/**
 *@author: mao.ye
 *@createTime: 2022/9/25 16:24
 *@desc: Application基类
 */
abstract class BaseApplication : MultiDexApplication() {
    private val logicList by lazy {
        ArrayList<Class<out BaseAppLogic>>()
    }
    private val logicClassList by lazy {
        ArrayList<BaseAppLogic>()
    }

    companion object {
        private var application: BaseApplication? = null
        fun getInstance(): BaseApplication {
            return application!!
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(base)
    }

    override fun onCreate() {
        super.onCreate()
        application = this
        initLogic()
        logicCreate()
    }

    /**
     * 主module调用
     */
    protected abstract fun initLogic()
    protected fun registerApplicationLogic(appLogic: Class<out BaseAppLogic>) {
        logicList.add(appLogic)
    }

    private fun logicCreate() {
        for (appLogicClass in logicList) {
            val appLogic = appLogicClass.newInstance()
            logicClassList.add(appLogic)
            appLogic.onCreate(this)
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        for (appLogic in logicClassList) {
            appLogic.onTerminate()
        }
    }

    override fun onLowMemory() {
        super.onLowMemory()
        for (appLogic in logicClassList) {
            appLogic.onLowMemory()
        }
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        for (appLogic in logicClassList) {
            appLogic.onTrimMemory(level)
        }
    }

}