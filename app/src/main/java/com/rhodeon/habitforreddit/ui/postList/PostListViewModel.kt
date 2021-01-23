package com.rhodeon.habitforreddit.ui.postList

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.rhodeon.habitforreddit.models.link.Link

/**
 * Created by Ruona Onobrakpeya on 12/30/20.
 */

class PostListViewModelFactory(private val subreddit: String) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostListViewModel::class.java)) {
            return PostListViewModel(subreddit) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}

class PostListViewModel(val subreddit: String) : ViewModel() {
    val posts: LiveData<PagingData<Link>> = fetchPosts(subreddit)

    private fun fetchPosts(location: String): LiveData<PagingData<Link>> {
        return PostListRepository(location).postListLiveData().cachedIn(viewModelScope)
    }
}