package com.rhodeon.habitforreddit.models

import com.squareup.moshi.Json

/**
 * Created by Ogheneruona Onobrakpeya on 12/23/20.
 * Source: https://github.com/reddit-archive/reddit/wiki/JSON#created-implementation
 */

data class Created (
    @field:Json(name = "created")val creationTimeLocal: Long,
    @field:Json(name = "creation_utc") val creationTimeUtc: Long
)