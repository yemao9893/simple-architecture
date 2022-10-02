package com.ym.viewframe.widget.clickstrategy

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout


/**
 *@author: mao.ye
 *@createTime: 2022/6/22 13:54
 *@desc: 普通的点击效果
 */
class NormalClickStrategy : ClickStrategy, View.OnTouchListener {
    private var decorView: FrameLayout? = null
    private var clickBackgroundColor = Color.parseColor("#99999999")
    override fun setTarget(vararg targets: View) {
        targets.forEach {
            it.setOnTouchListener(this)
        }
    }

    override fun recycle() {
        decorView = null
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        when (event!!.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                decorView = getDecorView(v)

                val clickView: View?
                if (v?.getTag(-1) == null) {
                    clickView = View(v?.context).apply {
                        val location = IntArray(2)
                        v!!.getLocationInWindow(location)
                        x = location[0].toFloat()
                        y = location[1].toFloat()
                        this.setBackgroundColor(clickBackgroundColor)
                        layoutParams = FrameLayout.LayoutParams(v.width, v.height)
                    }
                } else {
                    clickView = v.getTag(-1) as View
                }
                v?.setTag(-1, clickView)
                decorView?.addView(clickView)
            }
            MotionEvent.ACTION_UP -> {
                decorView?.removeView(v?.getTag(-1) as View)
            }
        }


        return false
    }

    private fun getDecorView(v: View?): FrameLayout? {
        if (decorView == null) {
            decorView = findDecorView(v) as FrameLayout?
        }
        return decorView
    }

    /**
     * 查找decorView
     */
    private fun findDecorView(v: View?): View {
        return if (v?.parent == null || v.javaClass.simpleName == "DecorView") {
            v!!
        } else {
            findDecorView(v.parent as View)
        }

    }
}