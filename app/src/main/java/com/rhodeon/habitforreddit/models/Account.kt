package com.rhodeon.habitforreddit.models

import com.squareup.moshi.Json

/**
 * Created by Ruona Onobrakpeya on 12/23/20.
 * Source: https://github.com/reddit-archive/reddit/wiki/JSON#account-implements-created
 */

data class Account(
    @field:Json(name = "comment_karma") val commentKarma: Int,
    @field:Json(name = "has_mail") val hasMail: Boolean?,
    @field:Json(name = "has_mod_mail") val hasModMail: Boolean?,
    @field:Json(name = "has_verified_email") val hasVerifiedEmail: Boolean,
    @field:Json(name = "id") val id: String,
    @field:Json(name = "inbox_count") val unreadMessages: Int?,
    @field:Json(name = "is_friend") val isFriend: Boolean,
    @field:Json(name = "is_gold") val isGold: Boolean,
    @field:Json(name = "is_mod") val isMod: Boolean,
    @field:Json(name = "link_karma") val linkKarma: Int,
    @field:Json(name = "name") val username: String,
    @field:Json(name = "over_18") val isNsfw: Boolean
)
