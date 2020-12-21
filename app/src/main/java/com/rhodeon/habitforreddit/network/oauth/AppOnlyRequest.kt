package com.rhodeon.habitforreddit.network.oauth

import android.util.Log
import com.rhodeon.habitforreddit.models.ApplicationOnlyResponse
import com.rhodeon.habitforreddit.utils.DEVICE_ID
import com.rhodeon.habitforreddit.utils.GRANT_TYPE
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by Ogheneruona Onobrakpeya on 12/19/20.
 */

fun applicationOnlyRequest() {
    var applicationOnlyResponse: ApplicationOnlyResponse?

    CoroutineScope(Dispatchers.IO).launch {
        try {
            val response = oAuthService().installedClient(
                GRANT_TYPE,
                DEVICE_ID
            )
            applicationOnlyResponse = response.body()

            if (applicationOnlyResponse != null) {
                response.message()
                Log.i("RequestToken", "response: $applicationOnlyResponse")
            }

            else {
                Log.i("RequestToken", "response is null: ${response.code()}")
            }
        }

        catch (e: Exception) {
            Log.e("RequestToken","Error: $e")
        }
    }
}