package com.rhodeon.habitforreddit.utils

import com.rhodeon.habitforreddit.BuildConfig

/**
 * Created by Ruona Onobrakpeya on 12/21/20.
 */

const val DEVICE_ID = "DO_NOT_TRACK_THIS_DEVICE"
const val GRANT_TYPE = "https://oauth.reddit.com/grants/installed_client"
const val USER_AGENT = "android:${BuildConfig.APPLICATION_ID}:v${BuildConfig.VERSION_NAME}"
const val DEFAULT_LISTING_LIMIT = 25

const val SUBREDDIT_PREFIX = "r/"
const val USERNAME_PREFIX = "u/"