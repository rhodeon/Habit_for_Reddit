package com.rhodeon.habitforreddit.ui.postList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rhodeon.habitforreddit.databinding.ItemPostListLoaderBinding

class PostListLoadStateAdapter : LoadStateAdapter<PostListLoaderViewHolder>() {
    override fun onBindViewHolder(holder: PostListLoaderViewHolder, loadState: LoadState) {
        // TODO("Implement retry for error state")
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): PostListLoaderViewHolder {
        val binding = ItemPostListLoaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostListLoaderViewHolder(binding)
    }
}

class PostListLoaderViewHolder(binding: ItemPostListLoaderBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Loading) {
        }
    }
}