package com.roviery.catetin.home.deadline

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.roviery.catetin.databinding.FragmentDeleteDialogBinding
import com.roviery.catetin.home.HomeViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class DeleteDialogFragment() : DialogFragment() {

    private val homeViewModel: HomeViewModel by viewModel()
    private var _binding: FragmentDeleteDialogBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDeleteDialogBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val deadline = DeleteDialogFragmentArgs.fromBundle(arguments as Bundle).deadline
            binding?.dialogBtnArchive?.setOnClickListener {

            }

            binding?.dialogBtnDelete?.setOnClickListener {
                homeViewModel.deleteDeadline(deadline)
                findNavController().navigateUp()
            }
        }

    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, 450)
    }

}