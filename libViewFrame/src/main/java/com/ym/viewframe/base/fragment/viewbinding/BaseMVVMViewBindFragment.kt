@file:Suppress("UNCHECKED_CAST")
package com.ym.viewframe.base.fragment.viewbinding

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.ym.viewframe.base.observer.StatusObserver
import com.ym.viewframe.base.viewmodel.*
import java.lang.reflect.ParameterizedType

/**
 *@author: mao.ye
 *@createTime: 2022/9/25 21:45
 *@desc: 所有MVVM的基类
 */
abstract class BaseMVVMViewBindFragment<VM : BaseViewModel, V : ViewDataBinding> :
    BaseViewBindingFragment<V>() {
    lateinit var viewModel: VM
    override fun onCreate(savedInstanceState: Bundle?) {
        val types = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments
        viewModel = ViewModelProvider(this)[types[0] as Class<VM>]
        super.onCreate(savedInstanceState)
        dataObserver()
    }

    private fun dataObserver() {
        viewModel.statusData.observe(this, StatusObserver(this))
    }
}