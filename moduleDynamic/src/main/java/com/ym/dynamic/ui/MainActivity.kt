package com.ym.dynamic.ui

import com.ym.dynamic.R
import com.ym.viewframe.base.activity.QuickActivity

class MainActivity : QuickActivity() {

    override fun getLayoutId() = R.layout.activity_main
    override fun getTitleValue() = "消息app"
}