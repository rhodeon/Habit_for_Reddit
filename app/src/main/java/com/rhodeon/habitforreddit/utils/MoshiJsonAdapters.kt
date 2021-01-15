package com.rhodeon.habitforreddit.utils

import com.rhodeon.habitforreddit.models.comment.CommentListing
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

/**
 * Created by Ruona Onobrakpeya on 1/15/21.
 */

fun mapToJson(map: Map<*, *>): String {
    val moshi = Moshi.Builder().build()
    val type = Types.newParameterizedType(Map::class.java, Any::class.java, Any::class.java)
    val adapter: JsonAdapter<Map<*, *>> = moshi.adapter(type)
    return adapter.toJson(map)
}

fun commentListingFromJson(repliesResponse: String): CommentListing {
    val moshi = Moshi.Builder().build()
    val type = Types.newParameterizedType(CommentListing::class.java)
    val adapter: JsonAdapter<CommentListing> = moshi.adapter(type)
    return adapter.fromJson(repliesResponse)!!
}