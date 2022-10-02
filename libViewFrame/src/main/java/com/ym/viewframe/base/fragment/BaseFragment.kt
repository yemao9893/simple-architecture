package com.ym.viewframe.base.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.ym.basic.expend.showToast
import com.ym.viewframe.R
import com.ym.viewframe.base.activity.iinterface.IStatusView
import com.ym.viewframe.base.viewmodel.StatusData


/**
 *@author: mao.ye
 *@createTime: 2022/10/2 12:00
 *@desc: fragment 基类
 */
abstract class BaseFragment : Fragment(), IStatusView {
    open var mContext: Context? = null
    private var isFirstVisible = false
    private var isViewCreated = false
    private var rootView: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isFirstVisible = true
        mContext = requireActivity()
    }

    @SuppressLint("InflateParams")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (rootView == null) {
            rootView =
                LayoutInflater.from(context).inflate(R.layout.fragment_base, null) as LinearLayout?
            rootView!!.addView(getLayoutView())
        }
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isViewCreated = true
    }


    override fun onResume() {
        super.onResume()
        initView()
        initData()
        if (isFirstVisible) {
            isFirstVisible = false
            lazyLoad()
        }
    }

    /**
     * 懒加载方法调用条件在Fragment处于显示状态
     */
    open fun lazyLoad() {
    }
    override fun onDestroy() {
        super.onDestroy()
        isViewCreated = false
//        App.getRefWatcher().watch(this)//监控那些本该被回收的对象
    }

    abstract fun getLayoutId(): Int

    abstract fun initView()

    open fun initData() {

    }

    open fun getLayoutView(): View? {
        return null
    }

    override fun showContentView() {

    }


    override fun showErrorView(errorData: StatusData) {

    }

    override fun showLoadingView() {

    }

    override fun showEmptyDataView() {

    }


    override fun showLoadingDialog(message: String?, isCancel: Boolean?) {
    }


    override fun dismissLoadingDialog() {

    }

    override fun onDetach() {
        super.onDetach()
        dismissLoadingDialog()
    }


    override fun showMessage(message: String) {
        showToast(message)
    }

}