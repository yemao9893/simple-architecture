package com.ym.network

import com.ym.network.http.RetrofitManager
import com.ym.network.http.api.API

/**
 *@author: mao.ye
 *@createTime: 2022/10/1 21:55
 *@desc: 网络请求使用的工具类
 */
object HttpApiHelper : API by RetrofitManager.getInstance().create(API::class.java)
