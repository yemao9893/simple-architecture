package com.ym.message.ui

import com.alibaba.android.arouter.facade.annotation.Route
import com.ym.message.R
import com.ym.message.databinding.MessageActivityMessageHomeBinding
import com.ym.router.RoutePath
import com.ym.viewframe.base.activity.databinding.BaseMVVMDataBindActivity
import com.ym.viewframe.base.viewmodel.BaseViewModel
@Route(path = RoutePath.MESSAGE_HOME_ACTIVITY)
class MessageHomeActivity : BaseMVVMDataBindActivity<BaseViewModel, MessageActivityMessageHomeBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.message_activity_message_home
    }
}