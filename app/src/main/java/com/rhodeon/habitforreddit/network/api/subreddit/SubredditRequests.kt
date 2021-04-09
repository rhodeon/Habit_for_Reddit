package com.rhodeon.habitforreddit.network.api.subreddit

import com.rhodeon.habitforreddit.models.link.LinkListing
import com.rhodeon.habitforreddit.utils.DEFAULT_LISTING_LIMIT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * Created by Ruona Onobrakpeya on 12/22/20.
 */

interface SubredditRequests {
    @GET
    suspend fun getPosts(
        @Url url: String,
        @Query("limit") limit: Int = DEFAULT_LISTING_LIMIT,
        @Query("before") before: String? = null,
        @Query("after") after: String? = null
    ): Response<LinkListing>
}
