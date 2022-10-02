package com.ym.viewframe.base.activity.databinding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.ym.viewframe.base.activity.QuickActivity

/**
 *@author: mao.ye
 *@createTime: 2022/9/25 21:37
 *@desc: DataBindingActivity基类
 */
abstract class BaseDataBindingActivity<T : ViewDataBinding> : QuickActivity() {
    lateinit var dataBinding: T
    override fun onCreate(savedInstanceState: Bundle?) {
        dataBinding =
            DataBindingUtil.bind(LayoutInflater.from(this).inflate(getLayoutId(), null, false))!!
        dataBinding.lifecycleOwner = this
        super.onCreate(savedInstanceState)
    }

    override fun getLayoutView(): View? {
        return dataBinding.root
    }
}