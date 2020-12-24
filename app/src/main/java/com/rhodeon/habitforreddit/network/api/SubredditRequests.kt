package com.rhodeon.habitforreddit.network.api

import android.util.Log
import com.rhodeon.habitforreddit.models.ApplicationOnlyResponse
import com.rhodeon.habitforreddit.models.Thing
import com.rhodeon.habitforreddit.network.oauth.ApplicationOnlyAuth
import com.rhodeon.habitforreddit.network.oauth.BasicAuthInterceptor
import com.rhodeon.habitforreddit.network.oauth.client
import com.rhodeon.habitforreddit.utils.DEVICE_ID
import com.rhodeon.habitforreddit.utils.GRANT_TYPE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Created by Ruona Onobrakpeya on 12/22/20.
 */

class SubredditRequests(val token: String) {
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
        @GET("/subreddit/funny/.json")
        suspend fun getSubreddit(
//            @Query("count") count: Int,
            @Query("limit") limit: Int
        ): Response<Thing>
    }

    suspend fun subreddits(): Thing? {
        return withContext(Dispatchers.IO) {
            try {
                val response = oAuthService2().getSubreddit(
//                count = 4,
                    limit = 0
                )
                val userlessResponse: Thing? = response.body()

                if (userlessResponse != null) {
                    response.message()
                    userlessResponse
                }
                else {
                    Log.e("RequestToken", "code: ${response.code()} message:${response.message()}")
                    Log.e("RequestToken", "${response.headers()}")

                    null
                }
            }
            catch (e: Exception) {
                Log.e("RequestToken", "Error Retrieving Access Token: $e")
                null
            }
        }

    }
}