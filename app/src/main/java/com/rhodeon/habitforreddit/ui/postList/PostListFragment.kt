package com.rhodeon.habitforreddit.ui.postList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.rhodeon.habitforreddit.MainNavDirections
import com.rhodeon.habitforreddit.databinding.FragmentPostListBinding
import com.rhodeon.habitforreddit.extensions.collapse
import com.rhodeon.habitforreddit.extensions.navigateSafe
import com.rhodeon.habitforreddit.models.link.Link
import kotlinx.coroutines.launch

/**
 * Created by Ruona Onobrakpeya on 1/2/21.
 */

class PostListFragment : Fragment() {
    private val args: PostListFragmentArgs by navArgs()
    private lateinit var postListViewModelFactory: PostListViewModelFactory
    private val postListViewModel: PostListViewModel by viewModels(
        factoryProducer = { postListViewModelFactory }
    )

    private var _binding: FragmentPostListBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: PostListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        postListViewModelFactory = PostListViewModelFactory(args.subreddit)
        _binding = FragmentPostListBinding.inflate(inflater, container, false)
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
        adapter = PostListAdapter {
            navigateToComments(it)
        }
        adapter.addLoadStateListener {
            // Collapse progress bar on initial loading
            if (it.append is LoadState.Loading) {
                binding.progressBar.collapse()
            }
        }

        val postListLoadStateAdapter = PostListLoadStateAdapter()
        binding.postRecyclerView.adapter = adapter.withLoadStateFooter(postListLoadStateAdapter)
    }

    private fun setUpObserver() {
        val viewModelObserver = Observer<PagingData<Link>> {
            lifecycleScope.launch {
                adapter.submitData(it)
            }
        }
        postListViewModel.posts.observe(viewLifecycleOwner, viewModelObserver)
    }

    private fun navigateToComments(link: Link) {
        val action = MainNavDirections.actionGlobalThreadFragment(
            permalink = link.data.permalink,
            post = link.data
        )
        navigateSafe(action)
    }
}

