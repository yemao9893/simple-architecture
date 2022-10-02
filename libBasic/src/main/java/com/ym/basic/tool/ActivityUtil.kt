package com.ym.basic.tool

import android.app.Activity
import java.util.*

/**
 *@author: mao.ye
 *@createTime: 2022/10/2 10:32
 *@desc: 管理、Activity相关的工具类
 */
class ActivityUtil {
    private val activityList by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        LinkedList<Activity>()
    }

    /**
     * 入栈
     */
    fun add(activity: Activity) {
        activityList.add(0, activity)
    }

    /**
     * 出栈
     */
    fun remove(activity: Activity) {
        activityList.remove(activity)
    }

    /**
     * 得到栈顶的Activity
     */
    fun getTopActivity(): Activity {
        if (activityList.isEmpty()) {
            throw NullPointerException("There is no activity in the stack")
        }
        return activityList.first
    }

    /**
     * 关闭栈内所有Activity
     */
    fun finishAllActivity() {
        for (activity in activityList) {
            activity.finish()
        }
        activityList.clear()
    }

    /**
     * 关闭除开栈底所有Activity
     */
    fun finishOtherActivity() {
    }

}