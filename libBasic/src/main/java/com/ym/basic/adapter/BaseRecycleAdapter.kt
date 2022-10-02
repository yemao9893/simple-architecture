package com.ym.basic.adapter

import android.view.LayoutInflater
import android.view.ViewGroup

/**
 *@author: mao.ye
 *@createTime: 2022/10/1 21:11
 *@desc: RecyclerView Adapter一般使用的基类
 */
abstract class BaseRecycleAdapter<T>(var layoutId: Int) : AbsRecycleAdapter<T, BaseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(layoutId, null, false)

        return BaseViewHolder(view).apply {
            addOnItemClickListener(this)
        }
    }
}