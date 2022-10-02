package com.ym.viewframe.widget.clickstrategy

import android.view.View

/**
 *@author: mao.ye
 *@createTime: 2022/6/22 13:36
 *@desc: 点击效果策略上下文
 */
class ClickContext(var clickStrategy: ClickStrategy) : ClickStrategy {
    override fun setTarget(vararg targets: View) {
        clickStrategy.setTarget(*targets)
    }

    override fun recycle() {
        clickStrategy.recycle()
    }

}