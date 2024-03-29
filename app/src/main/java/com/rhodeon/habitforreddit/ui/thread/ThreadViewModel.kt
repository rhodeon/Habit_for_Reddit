package com.rhodeon.habitforreddit.ui.thread

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rhodeon.habitforreddit.models.comment.CommentListing
import com.rhodeon.habitforreddit.network.api.APIService
import com.rhodeon.habitforreddit.utils.SessionManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by Ruona Onobrakpeya on 12/31/20.
 */

class CommentsViewModelFactory(private val permalink: String) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CommentsViewModel::class.java)) {
            return CommentsViewModel(permalink) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}

class CommentsViewModel(private val permalink: String) : ViewModel() {
    private val _response = MutableLiveData<List<CommentListing>>()
    val response: LiveData<List<CommentListing>> = _response

    init {
        CoroutineScope( Dispatchers.IO).launch {
            _response.postValue(getComments())
        }
    }

    private suspend fun getComments(): List<CommentListing>? {
        return withContext(Dispatchers.IO) {
            try {
                val response = APIService().threadRequests().getComments(permalink)

                val commentResponse: List<CommentListing>? = response.body()

                if (commentResponse != null) {
                    Log.i("CommentViewModel", "comment success: ${commentResponse[1]}" )
                    commentResponse
                }
                else {
                    Log.e("CommentViewModel", "code: ${response.code()} message:${response.message()}")
                    Log.e("CommentViewModel", "${response.headers()}")
                    null
                }
            }
            catch (e: Exception) {
                Log.e("CommentViewModel", "Error Retrieving Comments: $e")
                null
            }
        }
    }
}