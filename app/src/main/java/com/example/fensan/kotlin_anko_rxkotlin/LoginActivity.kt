package com.example.fensan.kotlin_anko_rxkotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
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

class LoginActivityUi : AnkoComponent<LoginActivity> {
    private val customStyle = { v: Any ->
        when (v) {
            is Button -> {
                v.textSizeDimen = R.dimen.login_button_size
                v.textColor = R.color.btn_login
            }
            is EditText -> {
                v.textSizeDimen = R.dimen.login_textSize_size
                v.textColor = R.color.edit_text
            }
        }
    }

    override fun createView(ui: AnkoContext<LoginActivity>) = with(ui) {
        verticalLayout {
            backgroundResource = R.mipmap.login_background

            val phone = editText {
                id = R.id.login_phone
                hintResource = R.string.edit_phone
                singleLine = true
            }.lparams(matchParent, wrapContent) {
                topMargin = dip(40)
                leftMargin = dip(20)
                rightMargin = dip(20)
            }

            val captcha = editText {
                id = R.id.login_captcha
                hintResource = R.string.edit_captcha
                singleLine = true

            }.lparams(matchParent, wrapContent) {
                topMargin = dip(10)
                leftMargin = dip(20)
                rightMargin = dip(20)
            }

            textView {
                textResource = R.string.user_agreement
                singleLine = true
                gravity = Gravity.CENTER_HORIZONTAL
                textSizeDimen = R.dimen.user_agreement_size
            }.lparams(matchParent, wrapContent) {
                topMargin = dip(10)
            }

            button {
                textResource = R.string.login
                backgroundResource = R.drawable.shape_login_btn_background
                onClick {
                    ui.owner.mPresenter.login(ui,
                            phone = phone.text,
                            captcha = captcha.text)
                }
            }.lparams(matchParent, wrapContent) {
                topMargin = dip(40)
                leftMargin = dip(20)
                rightMargin = dip(20)
            }
        }
    }.applyRecursively(customStyle)

}
