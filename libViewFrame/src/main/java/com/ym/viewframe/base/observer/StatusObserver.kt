package com.ym.viewframe.base.observer

import androidx.lifecycle.Observer
import com.ym.viewframe.base.activity.iinterface.IStatusView
import com.ym.viewframe.base.viewmodel.*

/**
 *@author: mao.ye
 *@createTime: 2022/10/2 14:43
 *@desc: null
 */
class StatusObserver(var statusView: IStatusView) : Observer<StatusData> {
    override fun onChanged(t: StatusData?) {
        when (t) {
            is ShowToast -> {
                statusView.showMessage(t.message)
            }
            is ShowLoadingDialog -> {
                statusView.showLoadingDialog(t.message, t.isCancel)
            }
            is ShowContentView -> {
                statusView.showContentView()
            }
            is ShowError -> {
                statusView.showErrorView(t)
            }
            is DismissLoadingDialog -> {
                statusView.dismissLoadingDialog()
            }
            else -> {}
        }
    }
}