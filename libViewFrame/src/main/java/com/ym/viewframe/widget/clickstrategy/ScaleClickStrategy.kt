package com.ym.viewframe.widget.clickstrategy

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.os.Build
import android.view.MotionEvent
import android.view.View

/**
 *@author: mao.ye
 *@createTime: 2022/6/22 13:33
 *@desc: 缩放点击效果
 */
class ScaleClickStrategy : ClickStrategy, View.OnTouchListener {
    private var animatorSet: AnimatorSet? = null

    override fun setTarget(vararg targets: View) {
        targets.map {
            it.setOnTouchListener(this)
        }
        val scaleXAnimator = ObjectAnimator.ofFloat(targets, "scaleX", 1f, 1.1f)
        scaleXAnimator?.duration = 100
        val scaleYAnimator = ObjectAnimator.ofFloat(targets, "scaleY", 1f, 1.1f)
        scaleYAnimator?.duration = 100

        animatorSet = AnimatorSet()
        animatorSet?.duration = 100
        animatorSet?.playTogether(scaleXAnimator, scaleYAnimator)
    }

    override fun recycle() {
        animatorSet?.cancel()
        animatorSet = null
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        when (event?.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                animatorSet?.childAnimations?.forEach {
                    it.setTarget(v)
                }
                animatorSet?.start()

            }
            MotionEvent.ACTION_UP -> {
                animatorSet?.childAnimations?.forEach {
                    it.setTarget(v)
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    animatorSet?.reverse()
                } else {
                    animatorSet?.childAnimations?.forEach {
                        if (it is ObjectAnimator) {
                            it.reverse()
                        }
                    }
                }
            }
        }
        return false
    }
}