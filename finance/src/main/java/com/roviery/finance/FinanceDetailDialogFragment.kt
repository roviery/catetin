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
import java.text.SimpleDateFormat
import java.util.*

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
                val newFDType = binding?.dialogAutocompleteTv?.text.toString()
                val newFDName = binding?.dialogEtName?.text.toString()
                val newFDExpenseString = binding?.dialogEtExpense?.text.toString()

                if (newFDType.isNotEmpty() && newFDName.isNotEmpty() && newFDExpenseString.isNotEmpty()) {
                    val newFinance = financeViewModel.getFinanceByType(newFDType)
                    val newFDExpense = Integer.parseInt(newFDExpenseString)

                    if (financeDetail != null) {
                        val oldFDType = financeDetail.financeDetailType
                        val oldFinance = financeViewModel.getFinanceByType(oldFDType)
                        val oldFDExpense = financeDetail.financeDetailExpense

                        // update finance detail
                        financeViewModel.updateFinanceDetail(
                            financeDetail,
                            newFDType,
                            newFDName,
                            newFDExpense
                        )

                        // update finance
                        if (newFDType != oldFDType) {
                            financeViewModel.updateFinance(
                                oldFinance,
                                oldFDType,
                                oldFinance.financeFundAllocation,
                                oldFinance.financeUsedFund - oldFDExpense,
                                oldFinance.financeRemainingFund + oldFDExpense
                            )

                            financeViewModel.updateFinance(
                                newFinance,
                                newFDType,
                                newFinance.financeFundAllocation,
                                newFinance.financeUsedFund + newFDExpense,
                                newFinance.financeRemainingFund - newFDExpense,
                            )
                        } else {
                            financeViewModel.updateFinance(
                                newFinance,
                                newFinance.financeType,
                                newFinance.financeFundAllocation,
                                newFinance.financeUsedFund + newFDExpense - oldFDExpense,
                                newFinance.financeRemainingFund - newFDExpense + oldFDExpense
                            )
                        }
                        findNavController().navigateUp()
                    } else {
                        // insert finance detail
                        val date = SimpleDateFormat("dd-MM-yyyy").format(Date())
                        val time = SimpleDateFormat("HH:mm").format(Date())
                        val dateTime = "$date  $time"
                        val newFinanceDetail = FinanceDetail(
                            0,
                            binding?.dialogAutocompleteTv?.text.toString(),
                            dateTime,
                            binding?.dialogEtName?.text.toString(),
                            Integer.parseInt(binding?.dialogEtExpense?.text.toString())
                        )
                        financeViewModel.insertFinanceDetail(newFinanceDetail)

                        // update finance
                        financeViewModel.updateFinance(
                            newFinance,
                            newFinance.financeType,
                            newFinance.financeFundAllocation,
                            newFinance.financeUsedFund + newFDExpense,
                            newFinance.financeRemainingFund - newFDExpense
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