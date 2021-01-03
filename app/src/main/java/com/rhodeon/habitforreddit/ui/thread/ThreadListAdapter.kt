package com.rhodeon.habitforreddit.ui.thread

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rhodeon.habitforreddit.databinding.ItemCommentBinding
import com.rhodeon.habitforreddit.models.comment.Comment
import com.rhodeon.habitforreddit.utils.DiffCallbackDelegate

/**
 * Created by Ruona Onobrakpeya on 12/31/20.
 */

class CommentsListAdapter : androidx.recyclerview.widget.ListAdapter<Comment, CommentsViewHolder>(
    DIFF_CALLBACK
) {
    companion object {
        val DIFF_CALLBACK : DiffUtil.ItemCallback<Comment> by DiffCallbackDelegate()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        val binding = ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class CommentsViewHolder(private val binding: ItemCommentBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(comment: Comment) {
        binding.commentBody.text = comment.data.rawBody
    }
}