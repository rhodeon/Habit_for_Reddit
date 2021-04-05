package com.rhodeon.habitforreddit.ui.postList

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.rhodeon.habitforreddit.models.link.Link
import com.rhodeon.habitforreddit.utils.SUBREDDIT_PREFIX
import com.rhodeon.habitforreddit.utils.USERNAME_PREFIX

/**
 * Created by Ruona Onobrakpeya on 12/30/20.
 */

lateinit var url: String

class PostListViewModelFactory(private val subreddit: String?, private val username: String?) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostListViewModel::class.java)) {
            return PostListViewModel(subreddit, username) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}

class PostListViewModel(val subreddit: String?, val username: String?) : ViewModel() {
    init {
        // Set query depending on host fragment
        url = if (subreddit == null) {
            "$USERNAME_PREFIX$username/submitted"   // User profile fragment
        }
        else {
            SUBREDDIT_PREFIX + subreddit  // Subreddit fragment
        }
    }
    val posts: LiveData<PagingData<Link>> = fetchPosts(url)

    private fun fetchPosts(location: String): LiveData<PagingData<Link>> {
        return PostListRepository(location).postListLiveData().cachedIn(viewModelScope)
    }
}