package com.rhodeon.habitforreddit.ui.subreddit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.rhodeon.habitforreddit.databinding.ItemPostHeaderBinding
import com.rhodeon.habitforreddit.models.link.Link
import com.rhodeon.habitforreddit.utils.DiffCallbackDelegate

/**
 * Created by Ruona Onobrakpeya on 12/23/20.
 */

class SubredditListAdapter(
    val clickHandler: (Link) -> Unit
) : androidx.recyclerview.widget.ListAdapter<Link, SubredditViewHolder>(DIFF_CALLBACK) {
    companion object {
        val DIFF_CALLBACK : DiffUtil.ItemCallback<Link> by DiffCallbackDelegate()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubredditViewHolder {
        val binding = ItemPostHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SubredditViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SubredditViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            clickHandler(getItem(position))
        }
    }
}

class SubredditViewHolder(private val binding: ItemPostHeaderBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(link: Link) {
        link.data.apply {
            binding.title.text = title
            binding.author.text = author
            binding.subreddit.text = subreddit
            binding.karma.text = score.toString()

            when(thumbnail) {
                "self", "image", "default" -> binding.thumbnail.isGone = true
                else -> binding.thumbnail.load(thumbnail)
            }
        }
    }
}