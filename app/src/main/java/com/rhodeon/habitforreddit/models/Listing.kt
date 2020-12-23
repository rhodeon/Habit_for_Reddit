package com.rhodeon.habitforreddit.models

/**
 * Created by Ruona Onobrakpeya on 12/23/20.
 * Source: https://github.com/reddit-archive/reddit/wiki/JSON#listing
 */

data class Listing(
    val before: String,
    val after: String,
    val modhash: String,
    val children: List<Thing>
)
