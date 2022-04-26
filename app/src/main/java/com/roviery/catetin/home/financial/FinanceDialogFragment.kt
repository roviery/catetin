package com.roviery.catetin.home.financial

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.roviery.catetin.R
import com.roviery.catetin.databinding.FragmentFinanceDialogBinding
import com.roviery.catetin.databinding.FragmentQuicknotesDialogBinding
import com.roviery.catetin.home.HomeViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class FinanceDialogFragment : BottomSheetDialogFragment() {

    private val homeViewModel: HomeViewModel by viewModel()
    private var _binding: FragmentFinanceDialogBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFinanceDialogBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null){

        }
    }
}