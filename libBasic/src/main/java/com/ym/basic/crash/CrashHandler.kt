package com.ym.basic.crash

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import com.ym.basic.tool.FileWriter
import java.io.PrintWriter
import java.io.StringWriter
import java.io.Writer
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 *@author: mao.ye
 *@createTime: 2022/10/2 11:01
 *@desc: 异常捕获类
 */
class CrashHandler : Thread.UncaughtExceptionHandler {
    private var context: Context? = null

    @SuppressLint("SimpleDateFormat")
    private val formatter: DateFormat = SimpleDateFormat("yyyy-MM-dd-HH-mm-ss")
    private var uncaughtExceptionHandler: Thread.UncaughtExceptionHandler? = null

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var crashHandler: CrashHandler? = null
        fun getInstance(): CrashHandler {
            return crashHandler ?: CrashHandler().also { crashHandler = it }
        }

    }

    /**
     * 调用此方法进行初始化
     * @param uncaughtExceptionHandler 外部可监听此方法进行监听异常
     */
    fun init(context: Context, uncaughtExceptionHandler: Thread.UncaughtExceptionHandler? = null) {
        this.context = context
        Thread.setDefaultUncaughtExceptionHandler(this)
        this.uncaughtExceptionHandler = uncaughtExceptionHandler
    }

    override fun uncaughtException(thread: Thread, e: Throwable) {
        FileWriter.writeLog(buildExceptionInfo(e))
        uncaughtExceptionHandler?.uncaughtException(thread, e)
    }

    /**¬
     * 构建异常信息
     */
    private fun buildExceptionInfo(e: Throwable): String {
        val sb = StringBuilder()
        sb.append("生产厂商：\n")
        sb.append(Build.MANUFACTURER).append("\n\n")
        sb.append("手机型号：\n")
        sb.append(Build.MODEL).append("\n\n")
        sb.append("系统版本：\n")
        sb.append(Build.VERSION.RELEASE).append("\n\n")
        sb.append("异常时间：\n")
        sb.append(formatter.format(Date())).append("\n\n")
        sb.append("异常类型：\n")
        sb.append(e.javaClass.name).append("\n\n")
        sb.append("异常信息：\n")
        sb.append(e.message).append("\n\n")
        sb.append("异常堆栈：\n")
        val writer: Writer = StringWriter()
        val printWriter = PrintWriter(writer)
        e.printStackTrace(printWriter)
        var cause = e.cause
        while (cause != null) {
            cause.printStackTrace(printWriter)
            cause = cause.cause
        }
        printWriter.close()
        val result = writer.toString()
        sb.append(result)
        return sb.toString()
    }
}