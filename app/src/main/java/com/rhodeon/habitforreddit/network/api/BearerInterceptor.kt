package com.rhodeon.habitforreddit.network.api

import com.rhodeon.habitforreddit.utils.USER_AGENT
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by Ruona Onobrakpeya on 12/22/20.
 */

class BearerInterceptor(private val token: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .addHeader("User-Agent", USER_AGENT)
            .build()

        return chain.proceed(request)
    }
}