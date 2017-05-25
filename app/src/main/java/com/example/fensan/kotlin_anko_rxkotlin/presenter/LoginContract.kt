package com.example.fensan.kotlin_anko_rxkotlin.presenter

import com.example.fensan.kotlin_anko_rxkotlin.LoginActivity
import com.example.fensan.kotlin_anko_rxkotlin.presenter.base.BasePresenter
import com.example.fensan.kotlin_anko_rxkotlin.presenter.base.BaseView
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.doAsync

/**
 * Created by fensan on 2017/5/25.
 */
interface LoginContract {

    interface View : BaseView {
        fun login()
        fun loginFailed()
    }

    class Presenter(mView: View) : BasePresenter<View>(mView) {

        fun login(ui: AnkoContext<LoginActivity>,
                  phone: CharSequence?,
                  captcha: CharSequence?){
            ui.doAsync {
                Thread.sleep(500)
                if(checkCredentials(phone.toString(), captcha.toString())) {
                    mView.login()
                } else {
                    mView.loginFailed()
                }
            }
        }
        private fun checkCredentials(name: String, password: String) = name == "user" && password == "password"
    }

}