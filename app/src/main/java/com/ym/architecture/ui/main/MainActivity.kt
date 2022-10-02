package com.ym.architecture.ui.main

import android.annotation.SuppressLint
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.ym.architecture.R
import com.ym.architecture.databinding.ActivityMainBinding
import com.ym.basic.tool.LogUtil
import com.ym.router.RoutePath
import com.ym.viewframe.base.activity.viewbinding.BaseMVVMViewBindActivity
import com.ym.viewframe.base.viewmodel.BaseViewModel
import com.ym.viewframe.widget.clickstrategy.ClickContext
import com.ym.viewframe.widget.clickstrategy.NormalClickStrategy
import com.ym.viewframe.widget.dialog.CommonBottomSheetDialog

@Route(path = RoutePath.APP_MAIN_ACTIVITY)
class MainActivity : BaseMVVMViewBindActivity<BaseViewModel, ActivityMainBinding>() {
    private val clickContext by lazy { ClickContext(NormalClickStrategy()) }

    override fun getViewBinding(): ActivityMainBinding {
        LogUtil.d("getViewBinding", "getViewBinding")
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getTitleValue(): CharSequence {
        return "MainActivity"
    }


    @SuppressLint("SetTextI18n")
    override fun initView() {
        super.initView()
        LogUtil.d("initView", "initView")
        hideRightTv()
        clickContext.setTarget(dataBinding.tvHello)
        dataBinding.tvHello.text = "dataBinding"
        dataBinding.tvHello.setOnClickListener {
            CommonBottomSheetDialog(this).apply {
                setContent("哈挖发文额废物恶妇啊好玩饿哦房间号")
                setNegativeButton(onDialogClickListener = object :
                    CommonBottomSheetDialog.OnDialogClickListener {
                    override fun onClick(dialog: CommonBottomSheetDialog) {
                        ARouter.getInstance().build(RoutePath.MESSAGE_HOME_ACTIVITY).navigation()
                        dialog.dismiss()
                    }
                })
            }.show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        clickContext.recycle()
    }

}