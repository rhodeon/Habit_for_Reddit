package com.rhodeon.habitforreddit.extensions

import android.view.View

/**
 * Created by Ruona Onobrakpeya on 1/3/21.
 */

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.INVISIBLE
}

fun View.collapse() {
    this.visibility = View.GONE
}