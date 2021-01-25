package com.kotlinmvp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity(), LoginPresenter.DoLoginCallBack,
    LoginPresenter.OnCheckUserState {
    private val loginPresenter by lazy {
        LoginPresenter()
    }
    private var userRegister = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initListener()
    }

    private fun initListener() {
        btnLogin.setOnClickListener {
            toLogin()
        }
        tvAccountInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                loginPresenter.checkUserState(s.toString(), this@LoginActivity)
            }

        })
    }

    private fun toLogin() {
        val account = tvAccountInput.text.toString()
        val password = tvPasswordInput.text.toString()

        if (!userRegister) {
            return
        }
        loginPresenter.doLogin(this, account, password)

    }

    override fun accountError() {
        tvTips?.text = "账号空"
    }

    override fun passwordError() {
        tvTips?.text = "密码空"
    }

    override fun startLogin() {
        tvTips?.text = "登录中..."
    }

    override fun loginSuccess() {
        tvTips?.text = "登录成功"
    }

    override fun loginFailed() {
        tvTips?.text = "登录失败"
    }

    override fun onNotExist() {
        tvTips?.text = "用户不存在"
        userRegister = false
    }

    override fun onExist() {
        tvTips?.text = "用户存在"
        userRegister = true
    }
}