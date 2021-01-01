package com.rhodeon.habitforreddit.ui.home

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.rhodeon.habitforreddit.databinding.FragmentHomeFeedBinding
import com.rhodeon.habitforreddit.models.link.Link
import com.rhodeon.habitforreddit.models.link.LinkListing
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by Ruona Onobrakpeya on 12/23/20.
 */

class HomeFeedFragment : Fragment() {
    private var _binding: FragmentHomeFeedBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeViewModelFactory: HomeViewModelFactory
    private val homeViewModel: HomeViewModel by viewModels(
        factoryProducer = { homeViewModelFactory }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val token = requireActivity().getSharedPreferences("vars", Context.MODE_PRIVATE)
            .getString("token", null)
        Toast.makeText(requireContext(), "tok: $token", Toast.LENGTH_SHORT).show()
        homeViewModelFactory = HomeViewModelFactory(token)

        _binding = FragmentHomeFeedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener {
            showBottomDialog()
        }

        val adapter = HomeListAdapter {
            navigateToComments(it)
        }
        binding.postRecyclerView.root.adapter = adapter

        val viewModelObserver = Observer<LinkListing> { response ->
            adapter.submitList(response.data.children)
        }
        homeViewModel.response.observe(viewLifecycleOwner, viewModelObserver)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun showBottomDialog() {
        findNavController()
            .navigate(HomeFeedFragmentDirections.actionHomeFeedFragmentToMenuBottomDialogFragment())
    }

    private fun navigateToComments(link: Link) {
        val token = requireActivity().getSharedPreferences("vars", Context.MODE_PRIVATE)
            .getString("token", null)

        CoroutineScope(Dispatchers.IO).launch {
            val action = HomeFeedFragmentDirections.actionHomeFeedFragmentToCommentsFragment(
                token = token!!,
                permalink = link.data.permalink
            )
            findNavController().navigate(action)
        }
    }
}