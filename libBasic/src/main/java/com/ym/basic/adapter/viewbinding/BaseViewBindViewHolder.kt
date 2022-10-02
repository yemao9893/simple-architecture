package com.ym.basic.adapter.viewbinding

import androidx.viewbinding.ViewBinding
import com.ym.basic.adapter.BaseViewHolder

/**
 *@author: mao.ye
 *@createTime: 2022/10/1 21:14
 *@desc: null
 */
class BaseViewBindViewHolder<DB : ViewBinding>(var binding: DB) : BaseViewHolder(binding.root) {
}