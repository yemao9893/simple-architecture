package com.ym.viewframe.base.activity.viewbinding

import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.ym.viewframe.base.activity.QuickActivity

/**
 *@author: mao.ye
 *@createTime: 2022/9/25 21:37
 *@desc: ViewBindingActivity基类
 */
abstract class BaseViewBindingActivity<T : ViewBinding> : QuickActivity() {
    lateinit var dataBinding: T
    override fun onCreate(savedInstanceState: Bundle?) {
        dataBinding = getViewBinding()
        super.onCreate(savedInstanceState)
    }

    abstract fun getViewBinding(): T
    override fun getLayoutId() = 0
    override fun getLayoutView() =dataBinding.root


}