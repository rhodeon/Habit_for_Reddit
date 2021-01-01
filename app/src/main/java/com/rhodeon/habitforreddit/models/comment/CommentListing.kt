package com.rhodeon.habitforreddit.models.comment

import com.rhodeon.habitforreddit.models.link.LinkListingData

/**
 * Created by Ruona Onobrakpeya on 12/31/20.
 */

data class CommentListing(
    val kind: String,
    val data: CommentListingData
)
