package com.rhodeon.habitforreddit.utils

import android.icu.text.TimeZoneFormat
import java.text.DateFormat
import java.util.*

/**
 * Created by Ruona Onobrakpeya on 1/19/21.
 */

fun formatDate(date: Long): String {
    return DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.SHORT).format(Date(date * 1000))
}