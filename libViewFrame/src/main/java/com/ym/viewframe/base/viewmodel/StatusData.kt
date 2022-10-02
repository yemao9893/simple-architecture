package com.ym.viewframe.base.viewmodel

/**
 *@author: mao.ye
 *@createTime: 2022/9/25 20:45
 *@desc: 状态相关的密封类
 */
sealed class StatusData
class ShowToast(var message: String) : StatusData()
class ShowLoadingDialog(var isCancel: Boolean?, var message: String? = null) : StatusData()
class ShowError(var code: Int, var message: String? = null) : StatusData()
object ShowContentView : StatusData()
object DismissLoadingDialog : StatusData()
