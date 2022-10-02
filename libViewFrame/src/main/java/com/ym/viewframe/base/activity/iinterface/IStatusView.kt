package com.ym.viewframe.base.activity.iinterface

import com.ym.viewframe.base.viewmodel.StatusData

/**
 *@author: mao.ye
 *@createTime: 2022/10/2 14:34
 *@desc: 通用状态接口
 */
interface IStatusView {
    fun showLoadingView()

    fun showEmptyDataView()

    fun showContentView()

    fun showLoadingDialog(message: String? = null, isCancel: Boolean? = false)

    fun showErrorView(errorData: StatusData)

    fun dismissLoadingDialog()

    fun showMessage(message: String)
}