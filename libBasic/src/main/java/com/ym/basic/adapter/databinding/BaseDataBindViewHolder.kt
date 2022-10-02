package com.ym.basic.adapter.databinding

import androidx.databinding.ViewDataBinding
import com.ym.basic.adapter.BaseViewHolder

/**
 *@author: mao.ye
 *@createTime: 2022/10/1 21:14
 *@desc: null
 */
class BaseDataBindViewHolder<DB : ViewDataBinding>(var binding:DB) : BaseViewHolder(binding.root) {
}