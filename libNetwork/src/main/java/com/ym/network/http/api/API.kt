package com.ym.network.http.api

import retrofit2.http.GET

/**
 *@author: mao.ye
 *@createTime: 2022/9/25 22:14
 *@desc: null
 */
interface API {
    @GET("/test")
    suspend fun test()
}