package com.rhodeon.habitforreddit.models

import com.squareup.moshi.Json

/**
 * Created by Ruona Onobrakpeya on 12/23/20.
 * Source: https://github.com/reddit-archive/reddit/wiki/JSON#votable-implementation
 */

data class Votable (
    @field:Json(name = "ups") val upvotes: Int,
    @field:Json(name = "down") val downvotes: Int,
    @field:Json(name = "likes") val isLiked: Boolean?
)