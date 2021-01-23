package com.rhodeon.habitforreddit.models.link

/**
 * Created by Ruona Onobrakpeya on 12/23/20.
 */

data class LinkListingData(
    val dist: Int?,
    val children: List<Link>,
    val before: String?,
    val after: String?
)
