package com.ym.viewframe.widget.clickstrategy

import android.view.View

/**
 *@author: mao.ye
 *@createTime: 2022/6/22 11:59
 *@desc: 点击效果策略
 */
interface ClickStrategy {
    /**
     * 设置目标
     */
    fun setTarget(vararg targets: View)

    /**
     * 回收
     */
    fun recycle()
}