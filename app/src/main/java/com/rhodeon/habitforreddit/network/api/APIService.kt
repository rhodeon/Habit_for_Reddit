package com.rhodeon.habitforreddit.network.api

import com.rhodeon.habitforreddit.network.api.subreddit.SubredditRequests
import com.rhodeon.habitforreddit.network.api.user.UserRequests
import com.rhodeon.habitforreddit.utils.SessionManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by Ruona Onobrakpeya on 09/04/2021.
 */

/**
 * A common class for creating retrofit instances for API requests
 */
class APIService {
    private val token = SessionManager.token

    private val client: OkHttpClient = OkHttpClient.Builder().apply {
        addInterceptor(BearerInterceptor(token))
    }.build()

    private val retrofitInstance: Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl("https://oauth.reddit.com")
        .client(client)
        .build()

    fun subredditRequests(): SubredditRequests = retrofitInstance.create(SubredditRequests::class.java)
    fun userRequests(): UserRequests = retrofitInstance.create(UserRequests::class.java)
    fun threadRequests(): ThreadRequests = retrofitInstance.create(ThreadRequests::class.java)
}
