package com.ym.basic.adapter

import android.annotation.SuppressLint
import android.content.Context
import androidx.recyclerview.widget.RecyclerView

/**
 *@author: mao.ye
 *@createTime: 2022/10/1 20:46
 *@desc: RecycleAdapter基类，子类可快速实现通用功能
 */
@Suppress("UNCHECKED_CAST")
abstract class AbsRecycleAdapter<T, VH : BaseViewHolder> : RecyclerView.Adapter<VH>() {
    val data by lazy { ArrayList<T>() }
    lateinit var context: Context
    var itemClickListener: OnItemClickListener<T>? = null

    override fun onBindViewHolder(holder: VH, position: Int) {
        convert(holder, position, data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newData: List<T>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    fun addData(newData: List<T>) {
        val count = data.size
        data.addAll(newData)
        if (count == data.size) {
            notifyItemRangeInserted(0, newData.size)
        } else {
            notifyItemRangeInserted(count - 1, newData.size)
        }
    }

    fun remove(position: Int) {
        data.removeAt(position)
        notifyItemRemoved(position)
    }

    fun removeAll() {
        val count = data.size
        data.clear()
        notifyItemRangeRemoved(0, count)
    }

    fun setOnItemClickListener(listener: OnItemClickListener<T>) {
        this.itemClickListener = listener
    }

    protected fun addOnItemClickListener(viewHolder: VH) {
        viewHolder.apply {
            itemView.setOnClickListener {
                itemClickListener?.onItemClick(data[layoutPosition], layoutPosition)
            }
        }
    }

    abstract fun convert(viewHolder: VH, position: Int, item: T)
    /**
     *@author: mao.ye
     *@createTime: 2022/10/1 21:34
     *@desc: item点击事件
     */
    interface OnItemClickListener<T> {
        fun onItemClick(item: T, position: Int)
    }
}