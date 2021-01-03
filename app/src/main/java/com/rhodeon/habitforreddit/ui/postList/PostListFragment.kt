package com.rhodeon.habitforreddit.ui.postList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.rhodeon.habitforreddit.MainNavDirections
import com.rhodeon.habitforreddit.databinding.FragmentPostListBinding
import com.rhodeon.habitforreddit.models.link.Link
import com.rhodeon.habitforreddit.models.link.LinkListing

/**
 * Created by Ruona Onobrakpeya on 1/2/21.
 */

class PostListFragment : Fragment() {
    private val args: PostListFragmentArgs by navArgs()
    private lateinit var postListViewModelFactory: PostListViewModelFactory
    private val postListViewModel: PostListViewModel by viewModels(
        factoryProducer = {postListViewModelFactory}
    )

    private var _binding: FragmentPostListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        postListViewModelFactory = PostListViewModelFactory(args.location)
        _binding = FragmentPostListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = PostListAdapter{
            navigateToComments(it)
        }
        binding.postRecyclerView.adapter = adapter

        val viewModelObserver = Observer<LinkListing> { response ->
            adapter.submitList(response.data.children)
        }
        postListViewModel.response.observe(viewLifecycleOwner, viewModelObserver)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun navigateToComments(link: Link) {
        val action = MainNavDirections.actionGlobalThreadFragment(
            permalink = link.data.permalink
        )
        findNavController().navigate(action)
    }
}