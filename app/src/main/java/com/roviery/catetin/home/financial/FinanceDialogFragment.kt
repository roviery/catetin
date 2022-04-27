package com.roviery.catetin.home.financial

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.roviery.catetin.databinding.FragmentFinanceDialogBinding
import com.roviery.catetin.home.HomeViewModel
import com.roviery.core.domain.model.Finance
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

        if (activity != null) {
            val finance = FinanceDialogFragmentArgs.fromBundle(arguments as Bundle).finance

            if (finance != null) {
                binding?.dialogEtType?.setText(finance.financeType)
                binding?.dialogEtFundAllocation?.setText(finance.financeFundAllocation.toString())
            }

            binding?.dialogBtnSave?.setOnClickListener {
                if (finance != null) {
                    homeViewModel.updateFinance(
                        finance,
                        binding?.dialogEtType?.text.toString(),
                        Integer.parseInt(binding?.dialogEtFundAllocation?.text.toString())
                    )
                    findNavController().navigateUp()
                } else {
                    val newFinance = Finance(
                        binding?.dialogEtType?.text.toString(),
                        Integer.parseInt(binding?.dialogEtFundAllocation?.text.toString()),
                        0,
                        Integer.parseInt(binding?.dialogEtFundAllocation?.text.toString())
                    )
                    homeViewModel.insertFinance(newFinance)
                    findNavController().navigateUp()
                }
            }
        }
    }
}