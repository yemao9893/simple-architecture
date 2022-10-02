package com.ym.architecture.ui.main

import com.ym.network.HttpApiHelper
import com.ym.viewframe.base.viewmodel.BaseViewModel

/**
 *@author: mao.ye
 *@createTime: 2022/9/25 22:21
 *@desc: null
 */
class MainViewModel : BaseViewModel() {
    fun getTest() {
        launch({
            HttpApiHelper.test()
        }, isShow = true)
    }
}