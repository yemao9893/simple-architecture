package com.ym.viewframe.base.activity

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.ym.basic.expend.showToast
import com.ym.basic.tool.StatusBarUtil
import com.ym.viewframe.R
import com.ym.viewframe.base.viewmodel.StatusData

/**
 *@author: mao.ye
 *@createTime: 2022/9/25 21:36
 *@desc:此类集成了app基础样式功能，子类继承此类可快速完成开发功能
 */
abstract class QuickActivity : BaseActivity() {
    private lateinit var tvTitle: TextView
    private lateinit var ivBack: ImageView
    private lateinit var tvBack: TextView
    private lateinit var tvRight: TextView
    private lateinit var ivRight: ImageView
    private lateinit var rlToolbar: RelativeLayout
    private lateinit var viewPadding: View
    private lateinit var llContent: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        tvTitle.text = getTitleValue()
        setDefaultToolbarStyle()
        initView()
    }

    @SuppressLint("InflateParams")
    override fun setContentView(layoutResID: Int) {
        val rootView = LayoutInflater.from(this)
            .inflate(R.layout.viewframe_activity_quick, null, false) as ViewGroup
        tvTitle = rootView.findViewById(R.id.tvTitle)
        ivBack = rootView.findViewById(R.id.ivBack)
        tvBack = rootView.findViewById(R.id.tvBack)
        tvRight = rootView.findViewById(R.id.tvRight)
        ivRight = rootView.findViewById(R.id.ivRight)
        rlToolbar = rootView.findViewById(R.id.rlToolbar)
        viewPadding = rootView.findViewById(R.id.viewPadding)
        llContent = rootView.findViewById(R.id.llContent)
        if (getLayoutView() == null) {
            val child = LayoutInflater.from(this).inflate(getLayoutId(), rootView, true)
            llContent.addView(child)
        } else {
            llContent.addView(
                getLayoutView(),
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT
            )
        }
        setContentView(rootView)
    }

    abstract fun getLayoutId(): Int
    open fun getLayoutView(): View? {
        return null
    }

    open fun initView() {}
    open fun getTitleValue(): CharSequence {
        return ""
    }

    /**
     * 设置左边按钮的点击监听
     */
    open fun setOnLiftClickListener(listener: View.OnClickListener) {
        ivBack.setOnClickListener {
            listener.onClick(it)
        }
        tvBack.setOnClickListener {
            listener.onClick(it)
        }
    }

    open fun setLeftBtnFinish() {
        tvBack.visibility = View.VISIBLE
        tvBack.setOnClickListener {
            finish()
        }
    }

    open fun setLeftImageFinish() {
        ivBack.visibility = View.VISIBLE
        ivBack.setOnClickListener {
            finish()
        }
    }

    open fun setLeftImageFinish(listener: View.OnClickListener) {
        ivBack.visibility = View.VISIBLE
        ivBack.setOnClickListener(listener)
    }

    open fun setRightBtn(value: CharSequence?, listener: View.OnClickListener) {
        tvRight.visibility = View.VISIBLE
        if (value?.isNotEmpty() == true)
            tvRight.text = value
        tvRight.setOnClickListener(listener)
    }

    open fun hideRightTv() {
        if (tvRight.visibility != View.GONE)
            tvRight.visibility = View.GONE
    }

    open fun showRightTv() {
        if (tvRight.visibility != View.VISIBLE)
            tvRight.visibility = View.VISIBLE
    }

    open fun getRightIv(): ImageView {
        return ivRight
    }

    open fun setRightImage(resId: Int, listener: View.OnClickListener) {
        ivRight.visibility = View.VISIBLE
        ivRight.setImageResource(resId)
        ivRight.setOnClickListener(listener)
    }

    private fun setDefaultToolbarStyle() {
        rlToolbar.background = viewPadding.background
        StatusBarUtil.setMargin(applicationContext, rlToolbar)
        StatusBarUtil.setHeightAndPadding(applicationContext, viewPadding)
        StatusBarUtil.transparencyBar(context)
        darkMode(true)
    }

    open fun setToolbarStyleByTransparent() {
        setToolbarStyle(Color.parseColor("#00000000"))
        viewPadding.visibility = View.GONE

    }

    open fun setToolbarStyle(
        backgroundColor: Int = Color.parseColor("#00000000"),
        backgroundResourceId: Int? = null
    ) {
        if (backgroundResourceId == null) {
            rlToolbar.setBackgroundColor(backgroundColor)
            viewPadding.setBackgroundColor(backgroundColor)
        } else {
            rlToolbar.setBackgroundResource(backgroundResourceId)
            viewPadding.setBackgroundResource(backgroundResourceId)
        }
        ivBack.setImageResource(com.ym.resouce.R.drawable.back_white)
        tvTitle.setTextColor(Color.parseColor("#ffffff"))
        tvRight.setTextColor(Color.parseColor("#ffffff"))
        darkMode(false)
    }

    open fun hideToolbar() {
        viewPadding.visibility = View.GONE
        rlToolbar.visibility = View.GONE
    }

    open fun showToolbar() {
        viewPadding.visibility = View.VISIBLE
        rlToolbar.visibility = View.VISIBLE
    }

    open fun darkMode(isDark: Boolean) {
        StatusBarUtil.darkMode(context, isDark)
    }

    override fun showLoadingView() {
    }

    override fun showEmptyDataView() {
    }

    override fun showContentView() {
    }

    override fun showLoadingDialog(message: String?, isCancel: Boolean?) {
    }

    override fun showErrorView(errorData: StatusData) {
    }

    override fun dismissLoadingDialog() {
    }

    override fun showMessage(message: String) {
        showToast(message)
    }


}