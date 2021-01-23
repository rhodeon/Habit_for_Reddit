package com.rhodeon.habitforreddit.ui.postList

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.rhodeon.habitforreddit.models.link.Link
import com.rhodeon.habitforreddit.utils.DEFAULT_LISTING_LIMIT

/**
 * Created by Ruona Onobrakpeya on 1/23/21.
 */

class PostListRepository(private val location: String) {
    private fun getDefaultPagingConfig() : PagingConfig {
        return PagingConfig(pageSize = DEFAULT_LISTING_LIMIT, enablePlaceholders = false)
    }

    fun postListLiveData() : LiveData<PagingData<Link>> {
        return Pager(
            config = getDefaultPagingConfig(),
            pagingSourceFactory = { PostListPagingSource(location) }
        ).liveData
    }
}