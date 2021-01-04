package com.rhodeon.habitforreddit.ui.subreddit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rhodeon.habitforreddit.databinding.FragmentSubredditBinding
import com.rhodeon.habitforreddit.ui.postList.PostListFragment
import com.rhodeon.habitforreddit.ui.postList.PostListFragmentArgs

/**
 * Created by Ruona Onobrakpeya on 12/30/20.
 */

class SubredditFragment : Fragment() {
    private val args: SubredditFragmentArgs by navArgs()
    private lateinit var subredditViewModelFactory: SubredditViewModelFactory
    private val subredditViewModel: SubredditViewModel by viewModels(
        factoryProducer = {subredditViewModelFactory}
    )

    private var _binding: FragmentSubredditBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        subredditViewModelFactory = SubredditViewModelFactory(args.location)

        _binding = FragmentSubredditBinding.inflate(inflater, container, false)

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTitle()

        val subredditStateAdapter = SubredditStateAdapter(this, args.location)
        binding.subredditPager.adapter = subredditStateAdapter
    }

    private fun setTitle() {
        binding.toolbar.title = subredditViewModel.location.capitalize()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

class SubredditStateAdapter(fragment: Fragment, private val location: String) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 1

    override fun createFragment(position: Int): Fragment {
        val fragment = PostListFragment()
        val args = PostListFragmentArgs(subreddit = location)
        fragment.arguments = args.toBundle()
        return fragment
    }
}