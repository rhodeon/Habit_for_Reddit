package com.rhodeon.habitforreddit.ui.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.rhodeon.habitforreddit.databinding.FragmentHomeFeedBinding
import com.rhodeon.habitforreddit.extensions.navigateSafe
import com.rhodeon.habitforreddit.ui.postList.PostListFragment
import com.rhodeon.habitforreddit.ui.postList.PostListFragmentArgs

/**
 * Created by Ruona Onobrakpeya on 12/23/20.
 */

class HomeFeedFragment : Fragment() {
    private val homeFeedViewModel: HomeFeedViewModel by viewModels()

    private var _binding: FragmentHomeFeedBinding? = null
    private val binding get() = _binding!!
    private lateinit var postStateAdapter: PostStateAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeFeedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpToolbar()
        setupViewPager()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setUpToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            showBottomDialog()
        }
    }
    private fun setupViewPager() {
        postStateAdapter = PostStateAdapter(this, homeFeedViewModel)
        binding.postPager.adapter = postStateAdapter

        TabLayoutMediator(binding.locationTab, binding.postPager) { tab, position ->
            tab.text = HomeFeedTab.values()[position].endpoint
        }.attach()
    }

    private fun showBottomDialog() {
        navigateSafe((HomeFeedFragmentDirections.actionHomeFeedFragmentToMenuBottomDialogFragment()))
    }
}

class PostStateAdapter(fragment: Fragment, private val viewModel: HomeFeedViewModel) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = HomeFeedTab.values().size

    override fun createFragment(position: Int): Fragment {
        val fragment = PostListFragment()
        val args = PostListFragmentArgs(subreddit = HomeFeedTab.values()[position].endpoint)
        fragment.arguments = args.toBundle()
        return fragment
    }
}