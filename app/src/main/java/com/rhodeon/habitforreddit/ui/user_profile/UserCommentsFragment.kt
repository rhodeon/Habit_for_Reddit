package com.rhodeon.habitforreddit.ui.user_profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.rhodeon.habitforreddit.databinding.FragmentUserCommentsBinding
import com.rhodeon.habitforreddit.extensions.collapse
import com.rhodeon.habitforreddit.models.comment.CommentListing

/**
 * Created by Ruona Onobrakpeya on 08/04/2021.
 */

class UserCommentsFragment : Fragment() {
    private val args: UserCommentsFragmentArgs by navArgs()
    private lateinit var viewModelFactory: UserCommentsViewModelFactory
    private val viewModel: UserCommentsViewModel by viewModels(
        factoryProducer = { viewModelFactory }
    )

    private var _binding: FragmentUserCommentsBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: UserCommentsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModelFactory = UserCommentsViewModelFactory(args.username)
        _binding = FragmentUserCommentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpAdapter()
        setUpObserver()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setUpAdapter() {
        adapter = UserCommentsListAdapter()
        binding.commentRecyclerView.adapter = adapter
    }

    private fun setUpObserver() {
        val viewModelObserver = Observer<CommentListing> { response ->
            adapter.submitList(response.data.children)
            binding.progressBar.collapse()
        }
        viewModel.response.observe(viewLifecycleOwner, viewModelObserver)
    }
}