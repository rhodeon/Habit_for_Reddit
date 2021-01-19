package com.rhodeon.habitforreddit.utils

import java.text.DateFormat
import java.util.*

/**
 * Created by Ruona Onobrakpeya on 1/19/21.
 */

fun formatDate(date: Long): String {
    return DateFormat.getDateInstance().format(Date(date * 1000))
}