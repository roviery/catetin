package com.roviery.finance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.roviery.core.ui.FinanceDetailAdapter
import com.roviery.finance.databinding.FragmentFinanceBinding
import org.koin.android.viewmodel.ext.android.viewModel

class FinanceFragment : Fragment() {

    private val financialViewModel: FinanceViewModel by viewModel()
    private var _binding: FragmentFinanceBinding? = null
    private val binding get() = _binding

    private lateinit var  financeDetailAdapter: FinanceDetailAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFinanceBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null){
            financeDetailAdapter = FinanceDetailAdapter()

            // Finance Detail Section

        }
    }

}