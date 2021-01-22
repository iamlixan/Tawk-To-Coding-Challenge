package com.lixan.fajardo.tawkentranceexam.ext

import android.view.View
import com.jakewharton.rxbinding3.view.clicks
import com.lixan.fajardo.tawkentranceexam.utils.NINJA_TAP_THROTTLE_TIME
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import java.util.concurrent.TimeUnit

fun View.ninjaTap(onNext: (View) -> Unit): Disposable {
    return this.clicks().throttleFirst(NINJA_TAP_THROTTLE_TIME, TimeUnit.MILLISECONDS)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeBy(
            onNext = {
                onNext.invoke(this)
            }
        )
}

fun View.setVisible(isVisible: Boolean) {
    this.visibility = if (isVisible) {
        View.VISIBLE
    } else {
        View.INVISIBLE
    }
}

fun View.gone() {
    this.visibility = View.GONE
}