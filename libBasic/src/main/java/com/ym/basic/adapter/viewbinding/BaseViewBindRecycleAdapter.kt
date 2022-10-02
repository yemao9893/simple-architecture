package com.ym.basic.adapter.viewbinding

import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.ym.basic.adapter.AbsRecycleAdapter

/**
 *@author: mao.ye
 *@createTime: 2022/10/1 21:20
 *@desc:  viewBinding 使用的通用RecyclerAdapter 基类
 */
abstract class BaseViewBindRecycleAdapter<T, VB : ViewBinding>() :
    AbsRecycleAdapter<T, BaseViewBindViewHolder<VB>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewBindViewHolder<VB> {
        context = parent.context
        return BaseViewBindViewHolder(getViewBinding()).apply {
            addOnItemClickListener(this)
        }
    }

    abstract fun getViewBinding(): VB
}