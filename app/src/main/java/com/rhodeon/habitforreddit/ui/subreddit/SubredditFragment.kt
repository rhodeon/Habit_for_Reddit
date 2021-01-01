package com.rhodeon.habitforreddit.ui.subreddit

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
import com.rhodeon.habitforreddit.databinding.FragmentSubredditBinding
import com.rhodeon.habitforreddit.models.link.LinkListing
import com.rhodeon.habitforreddit.ui.home.HomeFeedFragmentDirections

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

        val token = requireActivity().getSharedPreferences("vars", Context.MODE_PRIVATE)
            .getString("token", null)
        subredditViewModelFactory = SubredditViewModelFactory(token, args.location)

        _binding = FragmentSubredditBinding.inflate(inflater, container, false)

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigate(HomeFeedFragmentDirections.actionHomeFeedFragmentToMenuBottomDialogFragment())
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTitle()

        val adapter = SubredditListAdapter()
        binding.postRecyclerView.root.adapter = adapter

        val viewModelObserver = Observer<LinkListing> { response ->
            adapter.submitList(response.data.children)
        }
        subredditViewModel.response.observe(viewLifecycleOwner, viewModelObserver)


    }

    private fun setTitle() {
        binding.toolbar.title = subredditViewModel.location.capitalize()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}