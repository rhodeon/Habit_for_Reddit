package com.rhodeon.habitforreddit.ui.thread

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.rhodeon.habitforreddit.databinding.FragmentThreadBinding
import com.rhodeon.habitforreddit.extensions.collapse
import com.rhodeon.habitforreddit.extensions.show
import com.rhodeon.habitforreddit.models.comment.CommentListing
import com.rhodeon.habitforreddit.utils.bindPostHeader
import io.noties.markwon.Markwon

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

        val adapter = CommentsListAdapter()
        binding.commentRecyclerView.adapter = adapter

        val viewModelObserver = Observer<List<CommentListing>> { response ->
            adapter.submitList(response[1].data.children)
            binding.progressBar.collapse()
        }
        viewModel.response.observe(viewLifecycleOwner, viewModelObserver)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
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
}