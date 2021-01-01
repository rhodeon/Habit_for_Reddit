package com.rhodeon.habitforreddit.network.api.subreddit

import com.rhodeon.habitforreddit.models.comment.CommentListing
import com.rhodeon.habitforreddit.models.link.LinkListing
import com.rhodeon.habitforreddit.network.api.BearerInterceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

/**
 * Created by Ruona Onobrakpeya on 12/22/20.
 */

class SubredditRequests(private val token: String) {
    private val client: OkHttpClient = OkHttpClient.Builder().apply {
        addInterceptor(BearerInterceptor(token))
    }.build()

    fun oAuthService2(): Subreddit {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("https://oauth.reddit.com")
            .client(client)
            .build()

        return retrofit.create(Subreddit::class.java)
    }

    interface Subreddit {
        @GET
        suspend fun getPosts(
            @Url() url: String,
            @Query("limit") limit: Int
        ): Response<LinkListing>

        @GET
        suspend fun getComments(
            @Url() url: String
        ): Response<List<CommentListing>>
    }
}