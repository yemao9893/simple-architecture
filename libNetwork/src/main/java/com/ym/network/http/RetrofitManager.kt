package com.ym.network.http

import com.ym.network.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

/**
 *@author: mao.ye
 *@createTime: 2022/9/25 22:11
 *@desc: Retrofit管理类
 */

class RetrofitManager {
    companion object {
        private var retrofitManager: RetrofitManager? = null
        fun getInstance(): RetrofitManager {
            if (retrofitManager == null) {
                synchronized(this) {
                    if (retrofitManager == null)
                        retrofitManager = RetrofitManager()
                }
            }
            return retrofitManager!!
        }
    }

    private var retrofit: Retrofit

    init {
        val okHttpClient = OkHttpClient.Builder()
            .build()
        retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .build()

    }

    fun okHttpBuilder(
        connectTimeout: Long,
        readTimeout: Long,
        writeTimeout: Long,
        interceptors: ArrayList<Interceptor>
    ) {
        val okHttpClientBuilder = OkHttpClient.Builder()
        for (interceptor in interceptors) {
            okHttpClientBuilder.addInterceptor(interceptor)
        }
        okHttpClientBuilder
            .connectTimeout(connectTimeout, TimeUnit.SECONDS)
            .readTimeout(writeTimeout, TimeUnit.SECONDS)
            .writeTimeout(readTimeout, TimeUnit.SECONDS)
    }


    fun <T> create(clazz: Class<T>): T {
        return clazz.cast(retrofit.create(clazz))!!
    }
}