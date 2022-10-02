package com.ym.basic.adapter.databinding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.ym.basic.adapter.AbsRecycleAdapter

/**
 *@author: mao.ye
 *@createTime: 2022/10/1 21:20
 *@desc: dataBinding 使用的通用RecyclerAdapter 基类
 */
abstract class BaseDataBindRecycleAdapter<T, DB : ViewDataBinding>(var layoutId: Int) :
    AbsRecycleAdapter<T, BaseDataBindViewHolder<DB>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseDataBindViewHolder<DB> {
        context = parent.context
        val binding = DataBindingUtil.bind<DB>(
            LayoutInflater.from(parent.context).inflate(layoutId, null, false)
        )
        return BaseDataBindViewHolder(binding!!).apply {
            addOnItemClickListener(this)
        }
    }
}