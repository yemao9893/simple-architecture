package com.ym.viewframe.base.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ym.network.exception.ApiException
import kotlinx.coroutines.*

/**
 * @author: mao.ye
 * @createTime: 2022/9/25 20:44
 * @desc: viewModel基类
 */
typealias  Block<T> = suspend () -> T
typealias  Error = suspend (code: Int, message: String) -> Unit
typealias Cancel = suspend (exception: Exception) -> Unit

open class BaseViewModel : ViewModel() {
    val statusData by lazy {
        MutableLiveData<StatusData>()
    }

    fun launch(
        block: Block<Unit>,
        error: Error? = null,
        cancel: Cancel? = null,
        isShow: Boolean = false
    ) {
        viewModelScope.launch(Dispatchers.Default) {
            if (isShow)
                sendShowLoadingDialog()
            try {
                block.invoke()
            } catch (e: CancellationException) {
                sendDismissLoadingDialog()
                cancel?.invoke(e)
            } catch (e: Exception) {
                sendDismissLoadingDialog()
                handleError(e, error)
            }

        }
    }

    private suspend fun handleError(exception: Exception, error: Error?) {
        when (exception) {
            is ApiException -> {
                error?.invoke(0, "")
            }
            else -> {
                error?.invoke(0, "")
            }
        }
    }

    suspend fun sendShowToast(message: String) {
        withContext(Dispatchers.Main) {
            statusData.value = ShowToast(message)
        }
    }

    suspend fun sendShowLoadingDialog(isCancel: Boolean? = null, message: String? = null) {
        withContext(Dispatchers.Main) {
            statusData.value = ShowLoadingDialog(isCancel, message)
        }
    }

    suspend fun sendShowError(code: Int, message: String? = null) {
        withContext(Dispatchers.Main) {
            statusData.value = ShowError(code, message)
        }
    }

    suspend fun sendDismissLoadingDialog() {
        withContext(Dispatchers.Main) {
            statusData.value = DismissLoadingDialog
        }
    }

    suspend fun sendShowContentView() {
        withContext(Dispatchers.Main) {
            statusData.value = ShowContentView
        }
    }
}