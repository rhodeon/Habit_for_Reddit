package com.rhodeon.habitforreddit.network.api

import com.rhodeon.habitforreddit.models.comment.CommentListing
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * Created by Ruona Onobrakpeya on 09/04/2021.
 */

interface ThreadRequests {
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