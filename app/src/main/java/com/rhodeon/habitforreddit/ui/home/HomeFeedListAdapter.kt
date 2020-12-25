package com.rhodeon.habitforreddit.ui.home

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

class HomeViewHolder(private val binding: ItemPostHeaderBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(link: Link) {
        link.data.apply {
            binding.title.text = title
            binding.username.text = author
            binding.subreddit.text = subreddit
            binding.karma.text = score.toString()

            when(thumbnail) {
                "self", "image", "default" -> binding.thumbnail.isGone = true
                else -> binding.thumbnail.load(thumbnail)
            }
        }
    }

}

class HomeListAdapter : androidx.recyclerview.widget.ListAdapter<Link, HomeViewHolder>(DIFF_CALLBACK) {
    companion object {
        val DIFF_CALLBACK : DiffUtil.ItemCallback<Link> by DiffCallbackDelegate()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = ItemPostHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}