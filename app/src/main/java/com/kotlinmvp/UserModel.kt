package com.kotlinmvp

import android.text.TextUtils
import com.kotlinmvp.LoginPresenter.Companion.LOGIN_FAILED
import com.kotlinmvp.LoginPresenter.Companion.LOGIN_LOADING
import com.kotlinmvp.LoginPresenter.Companion.LOGIN_SUCCESS
import com.kotlinmvp.LoginPresenter.Companion.USER_EXIST
import com.kotlinmvp.LoginPresenter.Companion.USER_NOT_EXIST
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

open class UserModel {
    private val api by lazy { API() }
    private val random = Random()
    fun doLogin(account: String, password: String, block: (Int) -> Unit) {
        MainScope().launch {
            block.invoke(LOGIN_LOADING)
            api.login()
            val randomValue: Int = random.nextInt(3)
            delay(randomValue.toLong())
            if (randomValue == 0) {
                block.invoke(LOGIN_SUCCESS)
            } else {
                block.invoke(LOGIN_FAILED)
            }
        }
    }

    fun checkUserState(account: String, block: (Int) -> Unit) {
        block.invoke(if (TextUtils.isEmpty(account)) USER_NOT_EXIST else USER_EXIST)
    }
}
