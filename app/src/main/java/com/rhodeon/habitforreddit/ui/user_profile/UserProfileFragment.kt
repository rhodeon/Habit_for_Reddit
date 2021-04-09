package com.rhodeon.habitforreddit.ui.user_profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.rhodeon.habitforreddit.databinding.FragmentUserProfileBinding
import com.rhodeon.habitforreddit.ui.postList.PostListFragment
import com.rhodeon.habitforreddit.ui.postList.PostListFragmentArgs
import com.rhodeon.habitforreddit.utils.USERNAME_PREFIX

/**
 * Created by Ruona Onobrakpeya on 05/04/2021.
 */

class UserProfileFragment : Fragment() {
    private val args: UserProfileFragmentArgs by navArgs()
    private val userProfileViewModel: UserProfileViewModel by viewModels()

    private var _binding: FragmentUserProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var userStateAdapter: UserStateAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        setUpViewPager()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setupToolbar() {
        binding.toolbar.title = USERNAME_PREFIX + args.username

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setUpViewPager() {
        userStateAdapter = UserStateAdapter(this, args.username, userProfileViewModel)
        binding.userPager.adapter = userStateAdapter

        TabLayoutMediator(binding.userTab, binding.userPager) { tab, position ->
            tab.text = UserProfileTab.values()[position].name
        }.attach()
    }
}

class UserStateAdapter(
    fragment: Fragment,
    private val location: String,
    private val viewModel: UserProfileViewModel
) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = UserProfileTab.values().size

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> createPostListFragment()
            else -> createCommentListFragment()
        }
    }

    private fun createPostListFragment(): Fragment {
        val fragment = PostListFragment()
        val args = PostListFragmentArgs(username = location)
        fragment.arguments = args.toBundle()
        return fragment
    }

    private fun createCommentListFragment(): Fragment {
        val fragment = UserCommentsFragment()
        val args = UserCommentsFragmentArgs(username = location)
        fragment.arguments = args.toBundle()
        return fragment
    }
}