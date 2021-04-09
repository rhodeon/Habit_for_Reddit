package com.rhodeon.habitforreddit.ui.thread

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.rhodeon.habitforreddit.MainNavDirections
import com.rhodeon.habitforreddit.databinding.FragmentThreadBinding
import com.rhodeon.habitforreddit.extensions.collapse
import com.rhodeon.habitforreddit.extensions.navigateSafe
import com.rhodeon.habitforreddit.extensions.show
import com.rhodeon.habitforreddit.models.comment.CommentListing
import com.rhodeon.habitforreddit.utils.bindPostHeader
import io.noties.markwon.Markwon
import kotlinx.android.synthetic.main.item_post_header.*

/**
 * Created by Ruona Onobrakpeya on 12/31/20.
 */

class ThreadFragment : Fragment() {
    private val args: ThreadFragmentArgs by navArgs()
    private lateinit var factory: CommentsViewModelFactory
    private val viewModel: CommentsViewModel by viewModels(
        factoryProducer = { factory }
    )

    private var _binding: FragmentThreadBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: CommentsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        factory = CommentsViewModelFactory(args.permalink)
        _binding = FragmentThreadBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        bindPostData()
        setUpClickListeners()
        setUpAdapter()
        setUpObserver()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setUpAdapter() {
        adapter = CommentsListAdapter {
            navigateToAuthor(it)
            Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()
        }
        binding.commentRecyclerView.adapter = adapter
    }

    private fun setUpObserver() {
        val viewModelObserver = Observer<List<CommentListing>> { response ->
            adapter.submitList(response[1].data.children)
            binding.progressBar.collapse()
        }
        viewModel.response.observe(viewLifecycleOwner, viewModelObserver)
    }

    /**
     * Sets the data to display for the OP post header and selftext
     */
    private fun bindPostData() {
        val post = args.post
        bindPostHeader(post, binding.header)

        if (post.selftext.isNotBlank()) {
            binding.selftextLayout.show()
            val markwon = Markwon.create(requireContext())
            markwon.setMarkdown(binding.selftext, post.selftext)
        }
    }

    private fun setUpClickListeners() {
        val post = args.post

        binding.header.author.setOnClickListener {
            navigateToAuthor(post.author)
        }

        binding.header.subreddit.setOnClickListener {
            val action = MainNavDirections.actionGlobalSubredditFragment(location = post.subreddit)
            navigateSafe(action)
        }
    }

    private fun navigateToAuthor(author: String?) {
        val action = MainNavDirections.actionGlobalUserProfileFragment(username = author!!)
        navigateSafe(action)
    }
}