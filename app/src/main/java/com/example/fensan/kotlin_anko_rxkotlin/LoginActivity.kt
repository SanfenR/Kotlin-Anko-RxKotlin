package com.example.fensan.kotlin_anko_rxkotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.fensan.kotlin_anko_rxkotlin.presenter.LoginContract
import com.example.fensan.kotlin_anko_rxkotlin.presenter.LoginContract.Presenter
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class LoginActivity : AppCompatActivity(), LoginContract.View {

    lateinit var mPresenter: Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LoginActivityUi().setContentView(this)
        mPresenter = Presenter(this)
    }

    override fun login() {
        runOnUiThread {
            toast("Login Success !")
            startActivity<HomeActivity>()
        }
    }

    override fun loginFailed() {
        runOnUiThread {
            toast("Login Failed !")
        }
    }
}


class LoginActivityUi: AnkoComponent<LoginActivity> {
    override fun createView(ui: AnkoContext<LoginActivity>) = with(ui)  {
        verticalLayout {
            val phone = editText {
                id = R.id.login_phone
                hintResource = R.string.edit_phone
            }

            val captcha = editText {
                id = R.id.login_captcha
                hintResource = R.string.edit_captcha
            }

            textView {
                textResource = R.string.user_agreement
            }
            
            button {
                textResource = R.string.login
                onClick {
                    ui.owner.mPresenter.login(ui,
                            phone = phone.text,
                            captcha = captcha.text)
                }
            }
        }
    }
}
