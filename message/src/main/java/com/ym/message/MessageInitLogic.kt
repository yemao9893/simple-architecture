package com.ym.message

import android.app.Application
import com.ym.basic.application.BaseAppLogic
import com.ym.basic.tool.LogUtil

/**
 *@author: mao.ye
 *@createTime: 2022/9/25 20:07
 *@desc: 监听app启动
 */
class MessageInitLogic : BaseAppLogic() {
    override fun onCreate(application: Application) {
        super.onCreate(application)
        LogUtil.d("MessageApplication", "MessageInitLogic")
    }
}