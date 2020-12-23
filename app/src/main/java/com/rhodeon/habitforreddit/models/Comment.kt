package com.rhodeon.habitforreddit.models

import com.squareup.moshi.Json

/**
 * Created by Ruona Onobrakpeya on 12/23/20.
 * Source: https://github.com/reddit-archive/reddit/wiki/JSON#comment-implements-votable--created
 */

data class Comment (
    @field:Json(name = "author") val author: String,
    @field:Json(name = "author_flair_css_class") val authorFlairCss: String,
    @field:Json(name = "author_flair_text") val authorFlair: String,
    @field:Json(name = "body") val rawBody: String,
    @field:Json(name = "edited") val edited: Any,
    @field:Json(name = "gilded") val gildCount: Int,
    @field:Json(name = "link_author") val linkAuthor: String,
    @field:Json(name = "link_id") val linkId: String,
    @field:Json(name = "link_title") val linkTitle: String,
    @field:Json(name = "link_url") val linkUrl: String,
    @field:Json(name = "parent_id") val parentId: String,
    @field:Json(name = "replies") val replies: List<Thing>,
    @field:Json(name = "saved") val isSaved: Boolean,
    @field:Json(name = "score") val score: Int,
    @field:Json(name = "score_hidden") val isScoreHidden: Boolean,
    @field:Json(name = "subreddit") val subreddit: String,
    @field:Json(name = "subreddit_id") val subredditId: String,
    @field:Json(name = "distinguished") val distinguished: String?,

    // Implemented from Created
    @field:Json(name = "created")val creationTimeLocal: Long,
    @field:Json(name = "created_utc") val creationTimeUtc: Long,

    // Implemented from Votable
    @field:Json(name = "ups") val upvotes: Int,
    @field:Json(name = "down") val downvotes: Int,
    @field:Json(name = "likes") val isLiked: Boolean?,

    // For mods
    @field:Json(name = "approved_by") val approvedBy: String?,
    @field:Json(name = "banned_by") val bannedBy: String?,
    @field:Json(name = "num_reports") val reportCount: Int?
)
