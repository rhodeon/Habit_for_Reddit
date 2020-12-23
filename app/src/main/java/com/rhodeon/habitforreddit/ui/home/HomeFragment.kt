package com.rhodeon.habitforreddit.ui.home

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.rhodeon.habitforreddit.databinding.FragmentHomeBinding
import com.rhodeon.habitforreddit.models.Thing
import kotlinx.coroutines.CoroutineScope

/**
 * Created by Ruona Onobrakpeya on 12/23/20.
 */

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
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


        homeViewModelFactory = HomeViewModelFactory(token)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = HomeListAdapter()
        binding.postRecyclerView.adapter = adapter

        val viewModelObserver = Observer<Thing> { response ->
//            adapter.submitList(response.data.children)
        }

    }




    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}