package com.rhodeon.habitforreddit.ui.home

import android.util.Log
import androidx.lifecycle.*
import com.rhodeon.habitforreddit.models.link.LinkListing
import com.rhodeon.habitforreddit.network.api.subreddit.SubredditRequests
import com.rhodeon.habitforreddit.utils.SessionManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by Ruona Onobrakpeya on 12/23/20.
 */

class HomeViewModel : ViewModel() {
    private val _response = MutableLiveData<LinkListing>()
    val response: LiveData<LinkListing> = _response

    init {
        CoroutineScope( Dispatchers.IO).launch {
            updatePosts()
        }
    }

    suspend fun updatePosts() {
        withContext( Dispatchers.IO) {
            _response.postValue(loadPosts())
        }
    }

    private suspend fun loadPosts(): LinkListing? {
        return withContext(Dispatchers.IO) {
            try {
                val response = SubredditRequests(SessionManager.token).oAuthService2().getPosts(
                    url = "/r/popular/",
                    limit = 19
                )
                val postResponse: LinkListing? = response.body()

                if (postResponse != null) {
                    postResponse
                }
                else {
                    Log.e("HomeFeedViewModel", "code: ${response.code()} message:${response.message()}")
                    Log.e("HomeFeedViewModel", "${response.headers()}")
                    null
                }
            }
            catch (e: Exception) {
                Log.e("HomeFeedViewModel", "Error Retrieving Posts: $e")
                null
            }
        }
    }
}