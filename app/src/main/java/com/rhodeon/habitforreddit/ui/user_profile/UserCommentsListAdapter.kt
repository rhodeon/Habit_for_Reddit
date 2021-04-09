package com.rhodeon.habitforreddit.ui.user_profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rhodeon.habitforreddit.databinding.ItemCommentBinding
import com.rhodeon.habitforreddit.models.comment.Comment
import com.rhodeon.habitforreddit.utils.DiffCallbackDelegate
import com.rhodeon.habitforreddit.views.comment.CommentAdapter

/**
 * Created by Ruona Onobrakpeya on 08/04/2021.
 */

class UserCommentsListAdapter : androidx.recyclerview.widget.ListAdapter<Comment, UserCommentsViewHolder>(
    DIFF_CALLBACK
) {
    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Comment> by DiffCallbackDelegate()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserCommentsViewHolder {
        val binding =
            ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserCommentsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserCommentsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class UserCommentsViewHolder(val binding: ItemCommentBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(comment: Comment) {
        if (!comment.data.rawBody.isNullOrBlank()) {
            CommentAdapter(
                comment,
                binding.commentLayout,
                binding.commentLayout.context
            )
        }
    }
}