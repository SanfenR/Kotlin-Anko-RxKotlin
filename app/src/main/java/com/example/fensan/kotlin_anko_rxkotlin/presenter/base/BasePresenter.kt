package com.example.fensan.kotlin_anko_rxkotlin.presenter.base

/**
 * Created by fensan on 2017/5/25.
 */

abstract class BasePresenter<out T: BaseView>(protected val mView: T) {
}