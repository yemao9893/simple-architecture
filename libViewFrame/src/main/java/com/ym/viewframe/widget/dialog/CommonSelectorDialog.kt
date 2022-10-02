package com.ym.viewframe.widget.dialog

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ym.basic.adapter.AbsRecycleAdapter
import com.ym.basic.adapter.BaseRecycleAdapter
import com.ym.basic.adapter.BaseViewHolder
import com.ym.viewframe.R

/**
 * author: billy(mao.ye)
 * createTime: 2021/2/23
 * updateTime: 2021/2/23
 * description:公共选择器
 **/
//示例
//if (dialog == null) {
//    dialog = CommonSelectorDialog<SimpleSelectorData>(this).apply {
//        setData(viewModel.stationTypeData ?: arrayListOf())
//        setOnItemClickListener(object : CommonSelectorDialog.OnItemClickListener<SimpleSelectorData> {
//            override fun onItemClick(item: SimpleSelectorData, position: Int) {
//                dialog?.dismiss()
//                dialog(item)
//            }
//        })
//    }
//}
//dialog?.show()
class CommonSelectorDialog<T : CommonSelectorDialog.SelectorData>(context: Context) :
    CommonBottomSheetDialog(context),
    AbsRecycleAdapter.OnItemClickListener<T> {
    private var adapter: MyAdapter<T>
    private var onItemClickListener: OnItemClickListener<T>? = null

    init {
        val recyclerView = RecyclerView(context)
        adapter = MyAdapter()
        adapter.setOnItemClickListener(this)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        setChildView(recyclerView)
        setNegativeButton("取消", object : OnDialogClickListener {
            override fun onClick(dialog: CommonBottomSheetDialog) {
                dialog.dismiss()
            }
        })
    }

    /**
     * 选择项数据
     */
    fun setData(data: List<T>) {
        adapter.setSelectPosition(-1)
        adapter.setData(data)
    }

    /**
     * 选中的数据项
     */
    fun getSelectData(): T? {
        if (adapter.getSelectPosition() > 0) {
            return adapter.data[adapter.getSelectPosition()]
        }
        return null
    }

    /**
     * 获取选中项，小于0为未选中
     */
    fun getSelectPosition(): Int {
        return adapter.getSelectPosition()
    }

    /**
     * 设置选中项目
     */
    fun setSelectPosition(position: Int) {
        adapter.setSelectPosition(position)
    }


    /**
     * 监听点击项
     */
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener<T>) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onItemClick(item: T, position: Int) {
        if (adapter.getSelectPosition() > 0)
            adapter.notifyItemChanged(adapter.getSelectPosition())
        adapter.setSelectPosition(position)
        adapter.notifyItemChanged(position)
        onItemClickListener?.onItemClick(adapter.data[position], position)
    }

    class MyAdapter<T : SelectorData> : BaseRecycleAdapter<T>(R.layout.dialog_common_selector) {
        private var selectPosition = -1

        fun setSelectPosition(position: Int) {
            this.selectPosition = position
        }

        fun getSelectPosition(): Int {
            return selectPosition
        }

        override fun convert(viewHolder: BaseViewHolder, position: Int, item: T) {
            if (selectPosition == position) {
                viewHolder.setVisible(R.id.ivSelectorSelected, true)
            } else {
                viewHolder.setVisible(R.id.ivSelectorSelected, false)
            }
            viewHolder.setText(R.id.tvSelectorTitle, item.getTitle())
        }

    }

    /**
     * 选择项需实现此接口
     */
    interface SelectorData {
        fun getTitle(): String
    }


    /**
     * 可以使用默认的这个实体类来创建
     */
    data class SimpleSelectorData(var name: String) : SelectorData {
        override fun getTitle(): String {
            return name
        }
    }

    interface OnItemClickListener<T> {
        fun onItemClick(item: T, position: Int)
    }


}