package com.rhodeon.habitforreddit.ui.postList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rhodeon.habitforreddit.models.link.LinkListing
import com.rhodeon.habitforreddit.network.api.subreddit.SubredditRequests
import com.rhodeon.habitforreddit.utils.SessionManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by Ruona Onobrakpeya on 12/30/20.
 */

class PostListViewModelFactory(private val location: String) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostListViewModel::class.java)) {
            return PostListViewModel(location) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}

class PostListViewModel(val location: String) : ViewModel() {
    private val _response = MutableLiveData<LinkListing>()
    val response: LiveData<LinkListing> = _response

    init {
        CoroutineScope(Dispatchers.IO).launch {
            updatePosts()
        }
    }

    suspend fun updatePosts() {
        withContext(Dispatchers.IO) {
            _response.postValue(loadPosts())
        }
    }

    private suspend fun loadPosts(): LinkListing? {
        return withContext(Dispatchers.IO) {
            try {
                val response = SubredditRequests(SessionManager.token).oAuthService2().getPosts(
                    url = "/r/$location/",
                    limit = 19
                )
                val postResponse: LinkListing? = response.body()

                if (postResponse != null) {
                    postResponse
                }
                else {
                    Log.e(
                        "PostListViewModel",
                        "code: ${response.code()} message:${response.message()}"
                    )
                    Log.e("PostListViewModel", "${response.headers()}")
                    null
                }
            }
            catch (e: Exception) {
                Log.e("PostListViewModel", "Error Retrieving Posts: $e")
                null
            }
        }
    }
}