package com.rhodeon.habitforreddit.ui.home

import androidx.lifecycle.*
import com.rhodeon.habitforreddit.models.Thing
import com.rhodeon.habitforreddit.network.api.SubredditRequests
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Ruona Onobrakpeya on 12/23/20.
 */

class HomeViewModelFactory(private val token: String?): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(token) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}

class HomeViewModel(private val token: String?) : ViewModel() {
    private val _response = MutableLiveData<Thing>()
    val response: LiveData<Thing> = _response

    suspend fun getPosts() {
        withContext( Dispatchers.IO) {
            _response.value = SubredditRequests(token!!).subreddits()
        }
    }
}