package com.ym.viewframe.widget.dialog

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.ym.basic.tool.DensityUtil
import com.ym.viewframe.R

/**
 * author: billy(mao.ye)
 * createTime: 2021/2/19
 * updateTime: 2021/2/19
 * description:公共的底部弹窗对话框，底部默认左右两个按钮
 * 子类可继承它
 * @see CommonBottomSheetDialog.setChildView() //设置方法添加子布局
 **/
open class CommonBottomSheetDialog(
    context: Context,
    style: Int = R.style.CommonBottomSheetDialogStyle
) : BottomSheetDialog(context, style) {
    //标题
    private var tvTitle: TextView

    //正文
    private var tvContent: TextView

    //
    private var flContent: FrameLayout

    //次要按钮
    private var btnNegative: TextView

    //主要按钮
    private var btnPositive: TextView
    @SuppressLint("InflateParams")
    private var rootView: View =
        layoutInflater.inflate(R.layout.dialog_common_bottom_sheet, null, false)


    init {
        setContentView(rootView)
        tvTitle = rootView.findViewById(R.id.tvCommonBottomSheetTitle)!!
        tvContent = rootView.findViewById(R.id.tvCommonBottomSheetContent)!!
        flContent = rootView.findViewById(R.id.flCommonBottomSheetContent)!!
        btnNegative = rootView.findViewById(R.id.btnCommonBottomSheetNegative)!!
        btnPositive = rootView.findViewById(R.id.btnCommonBottomSheetPositive)!!
        rootView.findViewById<ImageView>(R.id.ivCommonClose)?.setOnClickListener {
            dismiss()
        }
        disableScrollToDown()

    }

    /**
     * 设置子布局
     * @param isMatchPrent 是否全屏
     */
    fun setChildView(view: View, isMatchPrent: Boolean = false): CommonBottomSheetDialog {
        if (isMatchPrent) {//设置最大高度
            val height = DensityUtil.getAndroidScreenPropertyHeight(context)
            val lp = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
            )
            lp.height = height * 3 / 5
            view.layoutParams = lp
        }
        flContent.removeAllViews()
        flContent.addView(view)
        tvContent.visibility = View.GONE

        rootView.postDelayed({
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
            behavior.peekHeight = rootView.measuredHeight
        }, 500)

        return this
    }

    /**
     * 动态修改子view高度
     */
    fun changeChildViewHeight(view: View, isMatchPrent: Boolean = false) {
        val height = DensityUtil.getAndroidScreenPropertyHeight(context)
        val lp = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.WRAP_CONTENT
        )
        if (isMatchPrent)
            lp.height = height * 3 / 5
        view.layoutParams = lp

    }

    /**
     * 设置标题
     */
    fun setTitleText(text: CharSequence): CommonBottomSheetDialog {
        tvTitle.text = text
        return this
    }

    /**
     * 设置次要按钮
     */
    open fun setNegativeButton(
        text: CharSequence = "取消",
        onDialogClickListener: OnDialogClickListener? = null
    ): CommonBottomSheetDialog {
        btnNegative.visibility = View.VISIBLE  //默认隐藏，设置时显示
        btnNegative.text = text
        btnNegative.setOnClickListener {
            onDialogClickListener?.onClick(this)
        }
        return this
    }

    /**
     * 主要按钮
     */
    open fun setPositiveButton(
        text: CharSequence = "确定",
        onDialogClickListener: OnDialogClickListener? = null
    ): CommonBottomSheetDialog {
        btnPositive.visibility = View.VISIBLE  //默认隐藏，设置时显示
        btnPositive.text = text
        btnPositive.setOnClickListener {
            onDialogClickListener?.onClick(this)
        }
        return this
    }

    /**
     * 设置内容
     */
    open fun setContent(text: CharSequence) {
        tvContent.text = text

    }

    override fun onStart() {
        super.onStart()
        //默认全屏
        if (behavior.state != BottomSheetBehavior.STATE_EXPANDED)
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    /**
     * 设置关闭点击事件
     */
    open fun setCloseListener(listener: View.OnClickListener) {
        rootView.findViewById<View>(R.id.ivCommonClose).setOnClickListener {
            listener.onClick(it)
        }
    }

    /**
     * 禁用向下滑动
     */
    private fun disableScrollToDown() {
        behavior.isHideable = false //此处设置表示禁止BottomSheetBehavior的执行
    }

    interface OnDialogClickListener {
        fun onClick(dialog: CommonBottomSheetDialog)
    }
}