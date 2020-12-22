package com.rhodeon.habitforreddit.network.oauth

import android.util.Log
import com.rhodeon.habitforreddit.models.ApplicationOnlyResponse
import com.rhodeon.habitforreddit.utils.DEVICE_ID
import com.rhodeon.habitforreddit.utils.GRANT_TYPE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Ogheneruona Onobrakpeya on 12/19/20.
 */

suspend fun requestAccessToken(): String? {
    var userlessResponse: ApplicationOnlyResponse?

    return withContext(Dispatchers.IO) {
        try {
            val response = oAuthService().installedClient(
                GRANT_TYPE,
                DEVICE_ID
            )
            userlessResponse = response.body()

            if (userlessResponse != null) {
                response.message()
                userlessResponse!!.accessToken
            }
            else {
                Log.e("RequestToken", "code: ${response.code()} message:${response.message()}")
                null
            }
        }
        catch (e: Exception) {
            Log.e("RequestToken", "Error Retrieving Access Token: $e")
            e.message
        }
    }
}