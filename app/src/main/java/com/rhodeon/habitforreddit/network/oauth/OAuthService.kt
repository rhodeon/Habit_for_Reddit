package com.rhodeon.habitforreddit.network.oauth

import com.rhodeon.habitforreddit.models.ApplicationOnlyResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by Ogheneruona Onobrakpeya on 12/19/20.
 */

val client: OkHttpClient = OkHttpClient.Builder().apply {
    addInterceptor(BasicAuthInterceptor())
}.build()

fun oAuthService(): ApplicationOnlyAuth {
    val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl("https://www.reddit.com")
        .client(client)
        .build()

    return retrofit.create(ApplicationOnlyAuth::class.java)
}

interface ApplicationOnlyAuth {
    @FormUrlEncoded
    @POST("/api/v1/access_token")
    suspend fun installedClient(
        @Field("grant_type") grantType: String,
        @Field("device_id") deviceId: String
    ): Response<ApplicationOnlyResponse>
}