package com.ym.viewframe.widget.clickstrategy

import android.animation.Animator
import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View
import android.view.ViewAnimationUtils

/**
 *@author: mao.ye
 *@createTime: 2022/6/22 14:48
 *@desc: 带按压效果的点击效果水波效果
 */
class WaterStrategy : ClickStrategy, View.OnTouchListener {
    private var clickAnimator: Animator? = null
    override fun setTarget(vararg targets: View) {
        targets.forEach {
            it.setOnTouchListener(this)
        }
    }

    override fun recycle() {
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(v: View, event: MotionEvent?): Boolean {
        when (event!!.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                clickAnimator = ViewAnimationUtils
                    .createCircularReveal(
                        v,
                        event.x.toInt(),//扩散中心点x
                        event.y.toInt(),//kuo's
                        v.width / 6f,
                        v.width / 4f
                    )
                clickAnimator?.duration = 500
                clickAnimator?.start()
            }
            MotionEvent.ACTION_UP -> {
                clickAnimator?.cancel()
            }
        }
        return false
    }
}