package com.rhodeon.habitforreddit.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.rhodeon.habitforreddit.databinding.FragmentMenuBottomDialogBinding

/**
 * Created by Ruona Onobrakpeya on 12/30/20.
 */

class MenuBottomDialogFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentMenuBottomDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBottomDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.goTo.setOnClickListener {
            goToClickHandler()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun goToClickHandler() {
        findNavController().navigate(MenuBottomDialogFragmentDirections.actionMenuBottomDialogFragmentToSearchDialogFragment())
    }
}