package com.rhodeon.habitforreddit.network.api.user

import com.rhodeon.habitforreddit.models.comment.CommentListing
import com.rhodeon.habitforreddit.models.link.LinkListing
import com.rhodeon.habitforreddit.network.api.BearerInterceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * Created by Ruona Onobrakpeya on 05/04/2021.
 */

interface UserRequests {
    @GET
    suspend fun getPosts(
        @Url url: String
    ): Response<LinkListing>

    @GET
    suspend fun getComments(
        @Url url: String
    ): Response<CommentListing>
}
