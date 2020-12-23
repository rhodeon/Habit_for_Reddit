package com.rhodeon.habitforreddit.models

/**
 * Created by Ruona Onobrakpeya on 12/22/20.
 */

/**
 * Reddit base class.
 * Source: https://github.com/reddit-archive/reddit/wiki/JSON#thing-reddit-base-class
 */
data class Thing(
    val id: String? = null,
    val name: String? = null,
    val kind: String,
    val data: Data
)
