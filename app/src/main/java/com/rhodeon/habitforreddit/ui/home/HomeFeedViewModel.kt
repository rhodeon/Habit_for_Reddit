package com.rhodeon.habitforreddit.ui.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.*
import com.rhodeon.habitforreddit.models.comment.CommentListing
import com.rhodeon.habitforreddit.models.link.Link
import com.rhodeon.habitforreddit.models.link.LinkListing
import com.rhodeon.habitforreddit.network.api.subreddit.SubredditRequests
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by Ruona Onobrakpeya on 12/23/20.
 */

class HomeViewModelFactory(private val token: String?): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(token) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}

class HomeViewModel(private val token: String?) : ViewModel() {
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
                val response = SubredditRequests(token!!).oAuthService2().getPosts(
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

    suspend fun getComments(link: Link) {
        /*return*/ withContext(Dispatchers.IO) {
            try {
                val response = SubredditRequests(token!!).oAuthService2().getComments(
                    url = link.data.permalink
                )

                val commentResponse: List<CommentListing>? = response.body()

                if (commentResponse != null) {
                    Log.i("HomeFeedViewModel", "comment success: ${commentResponse}" )
                }
                else {
                    Log.e("HomeFeedViewModel", "code: ${response.code()} message:${response.message()}")
                    Log.e("HomeFeedViewModel", "${response.headers()}")
                    null
                }
            }
            catch (e: Exception) {
                Log.e("HomeFeedViewModel", "Error Retrieving Comments: $e")
                null
            }

        }
    }
}