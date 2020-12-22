package com.rhodeon.habitforreddit.models

import com.squareup.moshi.Json

/**
 * Created by Ogheneruona Onobrakpeya on 12/19/20.
 */

/**
 * Holds the response for userless authentication
 */
data class ApplicationOnlyResponse(
    @field:Json(name = "access_token") val  accessToken: String,
    @field:Json(name= "token_type") val tokenType: String,
    @field:Json(name= "expires_in") val expiresIn: String,
    @field:Json(name= "scope") val scope: String
)
