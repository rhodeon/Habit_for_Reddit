package com.rhodeon.habitforreddit.models

import com.squareup.moshi.Json

/**
 * Created by Ogheneruona Onobrakpeya on 12/23/20.
 * Source: https://github.com/reddit-archive/reddit/wiki/JSON#message-implements-created
 */

data class Message(
    @field:Json(name = "author") val author: String,
    @field:Json(name = "body") val messageBody: String,
    @field:Json(name = "context") val context : String,
    @field:Json(name = "first_message") val firstMessageId : String?,
    @field:Json(name = "first_message_name") val firstMessageName : String,
    @field:Json(name = "likes") val isLiked: Boolean?,
    @field:Json(name = "link_title") val linkTitle: String,
    @field:Json(name = "name") val fullName: String,
    @field:Json(name = "new") val isNew: Boolean,
    @field:Json(name = "parent_id") val parentId: String?,
    @field:Json(name = "replies") val replies: String,
    @field:Json(name = "subject") val subject: String,
    @field:Json(name = "subreddit") val subreddit: String?,
    @field:Json(name = "was_comment") val wasComment: Boolean,

    // Implemented from Created
    @field:Json(name = "created")val creationTimeLocal: Long,
    @field:Json(name = "creation_utc") val creationTimeUtc: Long
)
