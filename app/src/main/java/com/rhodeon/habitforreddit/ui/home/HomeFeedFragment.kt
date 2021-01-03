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

/**
 * Created by Ruona Onobrakpeya on 12/23/20.
 */

class HomeFeedFragment : Fragment() {
    private var _binding: FragmentHomeFeedBinding? = null
    private val binding get() = _binding!!
    private val homeFeedViewModel: HomeFeedViewModel by viewModels()

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

        binding.toolbar.setNavigationOnClickListener {
            showBottomDialog()
        }

        val postStateAdapter = PostStateAdapter(this, homeFeedViewModel)
        binding.postPager.adapter = postStateAdapter

        TabLayoutMediator(binding.locationTab, binding.postPager) { tab, position ->
            tab.text = homeFeedViewModel.locationList[position]
        }.attach()

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun showBottomDialog() {
        navigateSafe((HomeFeedFragmentDirections.actionHomeFeedFragmentToMenuBottomDialogFragment()))
    }
}

class PostStateAdapter(fragment: Fragment, val viewModel: HomeFeedViewModel) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = viewModel.locationList.size

    override fun createFragment(position: Int): Fragment {
        val fragment = PostListFragment()
        fragment.arguments = Bundle().apply {
            putString("location", viewModel.locationList[position])
        }
        return fragment
    }
}