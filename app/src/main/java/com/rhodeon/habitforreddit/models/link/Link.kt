package com.rhodeon.habitforreddit.models.link

import com.squareup.moshi.Json

/**
 * Created by Ruona Onobrakpeya on 12/23/20.
 * Source: https://github.com/reddit-archive/reddit/wiki/JSON#link-implements-votable--created
 */

data class Link(
    @field:Json(name = "kind") val kind: String,
    @field:Json(name = "data") val data: LinkData
)

data class LinkData(
    @field:Json(name = "author") val author: String?,
    @field:Json(name = "author_flair_css_class") val authorFlairCss: String,
    @field:Json(name = "author_flair_text") val authorFlair: String,
    @field:Json(name = "clicked") val  isClicked: Boolean,
    @field:Json(name = "domain") val domain: String,
    @field:Json(name = "hidden") val isHidden: Boolean,
    @field:Json(name = "is_self") val isSelfpost: Boolean,
    @field:Json(name = "link_flair_css_class") val linkFlairCss: String,
    @field:Json(name = "link_flair_text") val linkFlair: String,
    @field:Json(name = "locked") val isLocked: Boolean,
    @field:Json(name = "num_comments") val commentCount: Int,
    @field:Json(name = "over_18") val isNsfw: Boolean,
    @field:Json(name = "permalink") val permalink: String,
    @field:Json(name = "saved") val isSaved: Boolean,
    @field:Json(name = "score") val score: Int,
    @field:Json(name = "selftext") val rawBody: String,
    @field:Json(name = "subreddit") val subreddit: String,
    @field:Json(name = "subreddit_id") val subredditId: String,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "url") val url: String,
    @field:Json(name = "edited") val edited: Any,
    @field:Json(name = "distinguished") val distinguished: String?,
    @field:Json(name = "stickied") val isStickied: Boolean,

    // Implemented from Created
    @field:Json(name = "created")val creationTimeLocal: Long,
    @field:Json(name = "created_utc") val creationTimeUtc: Long,

    // Implemented from Votable
    @field:Json(name = "ups") val upvotes: Int,
    @field:Json(name = "down") val downvotes: Int,
    @field:Json(name = "likes") val isLiked: Boolean?,

    // Media
    // TODO: Handle media objects.
    @field:Json(name = "media") val media: Any?,
    @field:Json(name = "media_embed") val mediaEmbed: Any,
    @field:Json(name = "thumbnail") val thumbnail: String
)
