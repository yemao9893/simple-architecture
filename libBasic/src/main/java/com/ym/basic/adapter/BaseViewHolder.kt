package com.ym.basic.adapter

import android.util.SparseArray
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 *@author: mao.ye
 *@createTime: 2022/10/1 20:33
 *@desc: ViewHolder 基类
 */
open class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val views by lazy { SparseArray<View>(10) }
    fun <T : View> getView(id: Int, clazz: Class<T>): T? {
        var view = views[id]
        if (view == null) {
            view = itemView.findViewById<T>(id)
        }
        return clazz.cast(view)
    }

    fun setText(id: Int, value: CharSequence) {
        getView(id, TextView::class.java)!!.text = value
    }

    fun setImage(id: Int, url: String) {

    }

    fun setImage(id: Int, src: Int) {
        getView(id, ImageView::class.java)!!.setImageResource(src)
    }

    fun setVisible(id: Int, visible: Boolean) {
        if (visible) {
            getView(id, View::class.java)?.visibility = View.VISIBLE
        } else {
            getView(id, View::class.java)?.visibility = View.GONE
        }
    }


}