//package com.rhodeon.habitforreddit.ui.user_profile
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import com.rhodeon.habitforreddit.models.link.LinkListing
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.withContext
//
///**
// * Created by Ruona Onobrakpeya on 05/04/2021.
// */
//
//class UserProfileViewModel {
//    private val _response = MutableLiveData<LinkListing>()
//    val response: LiveData<LinkListing> = _response
//
//    init {
//        CoroutineScope(Dispatchers.IO).launch {
//            updatePosts()
//        }
//    }
//
//    suspend fun updatePosts() {
//        withContext(Dispatchers.IO) {
//            _response.postValue(loadPosts())
//        }
//    }
//}