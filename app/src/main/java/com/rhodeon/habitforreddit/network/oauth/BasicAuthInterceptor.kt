package com.rhodeon.habitforreddit.network.oauth

import android.util.Base64
import com.rhodeon.habitforreddit.BuildConfig.REDDIT_CLIENT_ID
import com.rhodeon.habitforreddit.utils.USER_AGENT
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by Ogheneruona Onobrakpeya on 12/19/20.
 */

class BasicAuthInterceptor : Interceptor {
    private val credentials = "$REDDIT_CLIENT_ID:"
    private val encodedCredentials: String = Base64.encodeToString(credentials.toByteArray(), Base64.NO_WRAP)

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Authorization", "Basic $encodedCredentials")
            .addHeader("User-Agent", USER_AGENT)
            .build()

        return chain.proceed(request)
    }
}