package com.rhodeon.habitforreddit.ui.user_profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rhodeon.habitforreddit.databinding.FragmentUserProfileBinding
import com.rhodeon.habitforreddit.ui.postList.PostListFragment
import com.rhodeon.habitforreddit.ui.postList.PostListFragmentArgs

/**
 * Created by Ruona Onobrakpeya on 05/04/2021.
 */
class UserProfileFragment : Fragment() {
    private val args: UserProfileFragmentArgs by navArgs()

    private var _binding: FragmentUserProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserProfileBinding.inflate(inflater, container, false)

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTitle()

        val userStateAdapter = UserStateAdapter(this, args.username)
        binding.userPager.adapter = userStateAdapter
    }

    private fun setTitle() {
        binding.toolbar.title = args.username
    }
}

class UserStateAdapter(fragment: Fragment, private val location: String) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 1

    override fun createFragment(position: Int): Fragment {
        val fragment = PostListFragment()
        val args = PostListFragmentArgs(username = location)
        fragment.arguments = args.toBundle()
        return fragment
    }
}