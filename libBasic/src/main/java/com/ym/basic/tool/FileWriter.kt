package com.ym.basic.tool

/**
 *@author: mao.ye
 *@createTime: 2022/10/2 11:14
 *@desc: 写入文件的工具类
 */
object FileWriter {
    fun writeLog(path: String, name: String, value: String) {

    }

    fun writeLog(value: String) {
        writeLog("", "", value)
    }

}