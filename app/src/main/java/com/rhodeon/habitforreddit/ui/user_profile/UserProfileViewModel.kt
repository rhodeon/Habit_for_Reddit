package com.rhodeon.habitforreddit.ui.user_profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rhodeon.habitforreddit.models.link.LinkListing

/**
 * Created by Ruona Onobrakpeya on 05/04/2021.
 */

class UserProfileViewModel : ViewModel() {
    private val _response = MutableLiveData<LinkListing>()
    val response: LiveData<LinkListing> = _response
}