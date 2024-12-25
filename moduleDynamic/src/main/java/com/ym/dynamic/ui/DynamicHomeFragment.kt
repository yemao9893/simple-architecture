package com.ym.dynamic.ui

import com.alibaba.android.arouter.facade.annotation.Route
import com.ym.dynamic.R
import com.ym.dynamic.databinding.DynamicActivityDynamicHomeBinding

import com.ym.router.RoutePath
import com.ym.viewframe.base.activity.databinding.BaseMVVMDataBindActivity
import com.ym.viewframe.base.viewmodel.BaseViewModel

@Route(path = RoutePath.DYAMIC_HOME_ACTIVITY)
class DynamicHomeFragment :
    BaseMVVMDataBindActivity<BaseViewModel, DynamicActivityDynamicHomeBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.dynamic_activity_dynamic_home
    }

    override fun getTitleValue() = "消息"
    override fun initView() {
        super.initView()
        setLeftImageFinish()
    }
}