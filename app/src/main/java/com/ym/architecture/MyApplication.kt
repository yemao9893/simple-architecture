package com.ym.architecture

import com.alibaba.android.arouter.launcher.ARouter
import com.ym.basic.application.SimpleApplication
import com.ym.basic.tool.LogUtil
import com.ym.basic.tool.TipUtil
import com.ym.message.MessageInitLogic

/**
 *@author: mao.ye
 *@createTime: 2022/9/25 20:05
 *@desc: 程序入口
 */
class MyApplication : SimpleApplication() {
    override fun initLogic() {
        registerApplicationLogic(MessageInitLogic::class.java)
    }

    override fun onCreate() {
        super.onCreate()
        TipUtil.init(this)
        LogUtil.setDebug(BuildConfig.DEBUG)
        ARouter.init(this)
    }
}