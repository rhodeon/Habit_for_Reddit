package com.rhodeon.habitforreddit.ui.thread

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.rhodeon.habitforreddit.databinding.FragmentThreadBinding
import com.rhodeon.habitforreddit.extensions.collapse
import com.rhodeon.habitforreddit.models.comment.CommentListing

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

        val adapter = CommentsListAdapter()
        binding.commentRecyclerView.adapter = adapter
        binding.commentRecyclerView.setItemViewCacheSize(50)

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
}

class CustomManager(context: Context) : LinearLayoutManager(context) {
    override fun setInitialPrefetchItemCount(itemCount: Int) {
        super.setInitialPrefetchItemCount(50)
    }
}