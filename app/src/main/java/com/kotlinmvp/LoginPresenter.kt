package com.kotlinmvp

import android.text.TextUtils
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class LoginPresenter {
    //逻辑层 把Model层转换成View层能识别的内容
    companion object {
        const val USER_NOT_EXIST = 0
        const val USER_EXIST = 1
        const val LOGIN_LOADING = 0
        const val LOGIN_SUCCESS = 1
        const val LOGIN_FAILED = 2
    }

    private val userModel by lazy {
        UserModel()
    }

    fun checkUserState(account: String, callback: OnCheckUserState) {
        userModel.checkUserState(account) {
            when (it) {
                USER_NOT_EXIST -> {
                    callback.onNotExist()
                }
                USER_EXIST -> {
                    callback.onExist()
                }
            }
        }
    }

    fun doLogin(callBack: DoLoginCallBack, account: String, password: String) {
        MainScope().launch {
            if (TextUtils.isEmpty(account)) {
                callBack.accountError()
                return@launch
            }
            if (TextUtils.isEmpty(password)) {
                callBack.passwordError()
                return@launch
            }
            userModel.doLogin(account, password) {
                when (it) {
                    LOGIN_LOADING -> {
                        callBack.startLogin()
                    }
                    LOGIN_SUCCESS -> {
                        callBack.loginSuccess()
                    }
                    LOGIN_FAILED -> {
                        callBack.loginSuccess()
                    }

                }
            }
        }
    }

    interface OnCheckUserState {
        fun onNotExist()
        fun onExist()
    }

    interface DoLoginCallBack {
        fun accountError()
        fun passwordError()
        fun startLogin()
        fun loginSuccess()
        fun loginFailed()
    }
}