package com.rhodeon.habitforreddit.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.rhodeon.habitforreddit.databinding.FragmentSearchDialogBinding
import com.rhodeon.habitforreddit.extensions.navigateSafe

/**
 * Created by Ruona Onobrakpeya on 12/30/20.
 */

class SearchDialogFragment : DialogFragment() {
    private var _binding: FragmentSearchDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.goToButton.setOnClickListener {
            val location = binding.enterSubreddit.text.toString()
            navigateSafe(
                SearchDialogFragmentDirections.actionSearchDialogFragmentToSubredditFragment(
                    location
                )
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}