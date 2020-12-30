package com.rhodeon.habitforreddit.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.rhodeon.habitforreddit.databinding.FragmentHomeFeedBinding
import com.rhodeon.habitforreddit.models.link.LinkListing

/**
 * Created by Ruona Onobrakpeya on 12/23/20.
 */

class HomeFeedFragment : Fragment() {
    private var _binding: FragmentHomeFeedBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeViewModelFactory: HomeViewModelFactory
    private val homeViewModel: HomeViewModel by viewModels(
        factoryProducer = {homeViewModelFactory}
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
        setHasOptionsMenu(true)

        _binding = FragmentHomeFeedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = HomeListAdapter()
        binding.postRecyclerView.adapter = adapter
//        binding.postRecyclerView.addItemDecoration(DividerItemDecoration(this.requireContext(), DividerItemDecoration.VERTICAL))

        val viewModelObserver = Observer<LinkListing> { response ->
            adapter.submitList(response.data.children)
        }

//        val response = homeViewModel.response.value

        homeViewModel.response.observe(viewLifecycleOwner, viewModelObserver)

    }




    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}