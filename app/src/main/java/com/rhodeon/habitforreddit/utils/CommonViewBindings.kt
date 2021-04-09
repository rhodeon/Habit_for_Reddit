package com.rhodeon.habitforreddit.utils

import android.annotation.SuppressLint
import coil.load
import com.rhodeon.habitforreddit.databinding.ItemPostHeaderBinding
import com.rhodeon.habitforreddit.extensions.collapse
import com.rhodeon.habitforreddit.extensions.show
import com.rhodeon.habitforreddit.models.link.LinkData

/**
 * Created by Ruona Onobrakpeya on 1/20/21.
 */

/**
 * Binds the link data to the post header in post lists and threads.
 */
@SuppressLint("SetTextI18n")
fun bindPostHeader(postData: LinkData, binding: ItemPostHeaderBinding) {
    binding.apply {
        title.text = postData.title
        author.text = "$USERNAME_PREFIX${postData.author}"
        subreddit.text = "$SUBREDDIT_PREFIX${postData.subreddit}"
        karma.text = postData.score.toString()

        when (postData.thumbnail) {
            "self", "image", "default" -> thumbnail.collapse()
            else -> {
                thumbnail.show()
                thumbnail.load(postData.thumbnail)
            }
        }
    }
}