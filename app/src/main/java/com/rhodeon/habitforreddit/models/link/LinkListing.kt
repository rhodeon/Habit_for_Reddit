package com.rhodeon.habitforreddit.models.link


/**
 * Created by Ruona Onobrakpeya on 12/22/20.
 */

/**
 * Reddit base class.
 * Source: https://github.com/reddit-archive/reddit/wiki/JSON#thing-reddit-base-class
 */

data class LinkListing (
     val kind: String,
     val data: LinkListingData
)