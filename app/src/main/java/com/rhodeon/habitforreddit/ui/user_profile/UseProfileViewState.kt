package com.rhodeon.habitforreddit.ui.user_profile

/**
 * Created by Ruona Onobrakpeya on 09/04/2021.
 */

/**
 * Tabs displayed on user profiles
 */
enum class UserProfileTab(val endpoint: String) {
    Submitted("/submitted"),
    Comments("/comments")
}