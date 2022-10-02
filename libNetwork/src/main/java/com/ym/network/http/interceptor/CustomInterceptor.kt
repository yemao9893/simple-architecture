package com.ym.network.http.interceptor

import okhttp3.Interceptor
import okhttp3.Response

/**
 *@author: mao.ye
 *@createTime: 2022/10/1 22:06
 *@desc: 占位
 */
class CustomInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request())
    }
}