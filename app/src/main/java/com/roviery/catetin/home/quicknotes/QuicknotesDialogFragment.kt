package com.roviery.catetin.home.quicknotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.roviery.catetin.databinding.FragmentQuicknotesDialogBinding
import com.roviery.catetin.home.HomeViewModel
import com.roviery.core.domain.model.Quicknotes
import org.koin.android.viewmodel.ext.android.viewModel

class QuicknotesDialogFragment : BottomSheetDialogFragment() {

    private val homeViewModel: HomeViewModel by viewModel()
    private var _binding: FragmentQuicknotesDialogBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuicknotesDialogBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val quicknotes = QuicknotesDialogFragmentArgs.fromBundle(arguments as Bundle).quicknotes

            if (quicknotes != null) {
                binding?.dialogEtNotes?.setText(quicknotes.quicknotesText)
            }

            binding?.dialogIbCloseQuicknotes?.setOnClickListener {
                findNavController().navigateUp()
            }

            binding?.dialogBtnSave?.setOnClickListener {
                val notes = binding?.dialogEtNotes?.text.toString()

                if (notes.isNotEmpty()) {
                    if (quicknotes != null) {
                        homeViewModel.updateQuicknotes(
                            quicknotes,
                            binding?.dialogEtNotes?.text.toString(),
                        )
                        findNavController().navigateUp()
                    } else {
                        val newQuicknotes = Quicknotes(
                            0,
                            binding?.dialogEtNotes?.text.toString()
                        )
                        homeViewModel.insertQuicknotes(newQuicknotes)
                        findNavController().navigateUp()
                    }
                } else {
                    Toast.makeText(requireContext(), "Invalid Data", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }
}