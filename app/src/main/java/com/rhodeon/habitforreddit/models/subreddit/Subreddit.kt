package com.rhodeon.habitforreddit.models.subreddit

import com.squareup.moshi.Json

/**
 * Created by Ruona Onobrakpeya on 12/23/20.
 * Source: https://github.com/reddit-archive/reddit/wiki/JSON#subreddit
 */

data class Subreddit(
    @field:Json(name = "accounts_active") val activeAccounts: Int,
    @field:Json(name = "comment_score_hide_mins") val hiddenScoreDuration: Int,
    @field:Json(name = "description") val description: String,
    @field:Json(name = "display_name") val displayName:  String,
    @field:Json(name = "header_img") val headerImage: String?,
    @field:Json(name = "header_size") val headerSize: Array<Long>?,
    @field:Json(name = "header_title") val headerTitle:  String?,
    @field:Json(name = "over18") val isNsfw: Boolean,
    @field:Json(name = "public_description") val publicDescription: String,
    @field:Json(name = "public_traffic") val publicTraffic: Boolean,
    @field:Json(name = "subscribers") val subscriberCount: Long,
    @field:Json(name = "submission_type") val submissionType: String,
    @field:Json(name = "subreddit_type") val subredditType: String,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "url") val relativeUrl: String,
    @field:Json(name = "user_is_banned") val isUserBanned: Boolean,
    @field:Json(name = "user_is_contributor") val isUserContibutor:  Boolean,
    @field:Json(name = "user_is_moderator") val isUserModerator: Boolean,
    @field:Json(name = "user_is_subscriber") val isUserContributor: Boolean
)
