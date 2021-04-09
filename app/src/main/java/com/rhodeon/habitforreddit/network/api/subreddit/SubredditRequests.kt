package com.rhodeon.habitforreddit.network.api.subreddit

import com.rhodeon.habitforreddit.models.comment.CommentListing
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

    @GET
    suspend fun getComments(
        @Url url: String
    ): Response<List<CommentListing>>

    @GET("/api/morechildren")
    suspend fun moreComments(
        @Query("api_type") apiType: String = "json",
        @Query("children") children: List<String>,
        @Query("link_id") link: String,
        @Query("limit_children") limitChildren: Boolean = true,
        @Query("sort") sort: String = "top"
    ): Response<List<CommentListing>>
}
