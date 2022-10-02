package com.ym.viewframe.base.activity

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ym.viewframe.base.activity.iinterface.IStatusView

/**
 *@author: mao.ye
 *@createTime: 2022/9/25 21:35
 *@desc: null
 */
abstract class BaseActivity : AppCompatActivity(),IStatusView {
    var context: Activity? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        context = this
        super.onCreate(savedInstanceState)
    }
}