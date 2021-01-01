package com.rhodeon.habitforreddit.models.comment

/**
 * Created by Ruona Onobrakpeya on 12/23/20.
 */

data class CommentListingData(
    val dist: Int?,
    val children: List<Comment>
)
