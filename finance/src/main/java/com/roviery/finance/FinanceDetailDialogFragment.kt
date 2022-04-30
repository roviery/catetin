package com.roviery.finance

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.roviery.core.domain.model.FinanceDetail
import com.roviery.finance.databinding.FragmentFinanceDetailDialogBinding
import org.koin.android.viewmodel.ext.android.viewModel

class FinanceDetailDialogFragment : BottomSheetDialogFragment() {

    private val financeViewModel: FinanceViewModel by viewModel()
    private var _binding: FragmentFinanceDetailDialogBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFinanceDetailDialogBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val financeDetail =
                FinanceDetailDialogFragmentArgs.fromBundle(arguments as Bundle).financeDetail
            financeViewModel.listFinanceType.observe(viewLifecycleOwner) { financeType ->
                Log.d("FINANCE TYPE LIST", financeType.toString())
                val adapter = ArrayAdapter(requireContext(), R.layout.list_item, financeType)
                binding?.dialogAutocompleteTv?.setAdapter(adapter)
            }


            if (financeDetail != null) {
                binding?.dialogEtName?.setText(financeDetail.financeDetailName)
                binding?.dialogEtExpense?.setText(financeDetail.financeDetailExpense.toString())
            }

            binding?.dialogBtnSave?.setOnClickListener {
                val type = binding?.dialogAutocompleteTv?.text.toString()
                val name = binding?.dialogEtName?.text.toString()
                val expenseString = binding?.dialogEtExpense?.text.toString()

                if (type.isNotEmpty() && name.isNotEmpty() && expenseString.isNotEmpty()) {
                    val finance = financeViewModel.getFinanceWithType(type)
                    val expense = Integer.parseInt(expenseString)
                    if (financeDetail != null) {
                        // update finance detail
                        val tempExpense = financeDetail.financeDetailExpense
                        financeViewModel.updateFinanceDetail(
                            financeDetail,
                            type,
                            name,
                            expense
                        )

                        // update finance
                        financeViewModel.updateFinance(
                            finance,
                            finance.financeType,
                            finance.financeFundAllocation,
                            finance.financeUsedFund + expense - tempExpense,
                            finance.financeRemainingFund - expense + tempExpense
                        )
                        findNavController().navigateUp()
                    } else {
                        // insert finance detail
                        val newFinanceDetail = FinanceDetail(
                            0,
                            binding?.dialogAutocompleteTv?.text.toString(),
                            binding?.dialogEtName?.text.toString(),
                            Integer.parseInt(binding?.dialogEtExpense?.text.toString())
                        )
                        financeViewModel.insertFinanceDetail(newFinanceDetail)

                        // update finance
                        financeViewModel.updateFinance(
                            finance,
                            finance.financeType,
                            finance.financeFundAllocation,
                            finance.financeUsedFund + expense,
                            finance.financeRemainingFund - expense
                        )
                        findNavController().navigateUp()
                    }
                } else {
                    Toast.makeText(requireContext(), "Invalid Data", Toast.LENGTH_SHORT).show()
                }


            }
        }
    }
}