package com.rhodeon.habitforreddit.ui.postList

import android.util.Log
import androidx.paging.PagingSource
import com.rhodeon.habitforreddit.models.link.Link
import com.rhodeon.habitforreddit.models.link.LinkListing
import com.rhodeon.habitforreddit.network.api.APIService
import com.rhodeon.habitforreddit.utils.SessionManager

/**
 * Created by Ruona Onobrakpeya on 1/23/21.
 */

class PostListPagingSource(private val location: String) : PagingSource<String, Link>() {
    override suspend fun load(params: LoadParams<String>): LoadResult<String, Link> {
        val page = params.key

        // TODO: Handle exceptions properly
        try {
            val response = fetchPosts(location, page)

            response?.let {
                return LoadResult.Page(
                    data = it.data.children,
                    prevKey = it.data.before,
                    nextKey = it.data.after
                )
            }
        }
        catch (e: Exception) {
            return LoadResult.Error(e)
        }

        return LoadResult.Error(Exception())
    }

    private suspend fun fetchPosts(url: String, page: String?): LinkListing? {
        return try {
            val response = APIService().subredditRequests().getPosts(
                url = url,
                after = page
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

