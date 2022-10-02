package com.ym.viewframe.base.fragment.databinding

import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.ym.viewframe.base.fragment.BaseFragment

/**
 *@author: mao.ye
 *@createTime: 2022/9/25 21:37
 *@desc: DataBindingFragment基类
 */
abstract class BaseDataBindingFragment<T : ViewDataBinding> : BaseFragment() {
    lateinit var dataBinding: T
    override fun getLayoutId() = 0
    override fun getLayoutView(): View? {
        dataBinding =
            DataBindingUtil.bind(
                LayoutInflater.from(mContext).inflate(getLayoutId(), null, false)
            )!!
        dataBinding.lifecycleOwner = this
        return dataBinding.root
    }
}