package com.rhodeon.habitforreddit.ui.user_profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rhodeon.habitforreddit.models.comment.CommentListing
import com.rhodeon.habitforreddit.network.api.user.UserRequests
import com.rhodeon.habitforreddit.utils.SessionManager
import com.rhodeon.habitforreddit.utils.USERNAME_PREFIX
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by Ruona Onobrakpeya on 08/04/2021.
 */

class UserCommentsViewModelFactory(private val permalink: String) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserCommentsViewModel::class.java)) {
            return UserCommentsViewModel(permalink) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}

class UserCommentsViewModel(private val permalink: String) : ViewModel() {
    private val _response = MutableLiveData<CommentListing>()
    val response: LiveData<CommentListing> = _response

    init {
        CoroutineScope(Dispatchers.IO).launch {
            _response.postValue(getComments())
        }
    }

    private suspend fun getComments(): CommentListing? {
        return withContext(Dispatchers.IO) {
            try {
                val response = UserRequests(SessionManager.token).oAuthService2()
                    .getComments("${USERNAME_PREFIX}${permalink}${UserProfileTab.Comments.endpoint}")
                val commentResponse: CommentListing? = response.body()

                if (commentResponse != null) {
                    Log.i("UserCommentViewModel", "comment success: $commentResponse")
                    commentResponse
                }
                else {
                    Log.e(
                        "UserCommentViewModel",
                        "code: ${response.code()} message:${response.message()}"
                    )
                    Log.e("UserCommentViewModel", "${response.headers()}")
                    null
                }
            }
            catch (e: Exception) {
                Log.e("UserCommentViewModel", "Error Retrieving Comments: $e")
                null
            }
        }
    }
}