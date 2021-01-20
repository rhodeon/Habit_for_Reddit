package com.rhodeon.habitforreddit.ui.postList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rhodeon.habitforreddit.databinding.ItemPostHeaderBinding
import com.rhodeon.habitforreddit.models.link.Link
import com.rhodeon.habitforreddit.utils.DiffCallbackDelegate
import com.rhodeon.habitforreddit.utils.bindPostHeader

/**
 * Created by Ruona Onobrakpeya on 1/3/21.
 */

class PostListAdapter(
    val clickHandler: (Link) -> Unit
) : ListAdapter<Link, PostListViewHolder>(DIFF_CALLBACK) {
    companion object {
        val DIFF_CALLBACK : DiffUtil.ItemCallback<Link> by DiffCallbackDelegate()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostListViewHolder {
        val binding = ItemPostHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostListViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            clickHandler(getItem(position))
        }
    }
}

class PostListViewHolder(private val binding: ItemPostHeaderBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(link: Link) {
        bindPostHeader(link.data, binding)
    }
}