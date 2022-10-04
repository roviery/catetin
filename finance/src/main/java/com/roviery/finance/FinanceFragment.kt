package com.roviery.finance

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.roviery.core.ui.FinanceDetailAdapter
import com.roviery.core.ui.FinanceTypeAdapter
import com.roviery.core.utils.SwipeGesture
import com.roviery.finance.databinding.FragmentFinanceBinding
import com.roviery.finance.di.financeModule
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import java.text.NumberFormat
import java.util.*

class FinanceFragment : Fragment() {

    private val financeViewModel: FinanceViewModel by viewModel()
    private var _binding: FragmentFinanceBinding? = null
    private val binding get() = _binding

    private lateinit var financeDetailAdapter: FinanceDetailAdapter
    private lateinit var financeTypeAdapter: FinanceTypeAdapter
    private lateinit var filter: MutableList<String>

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

        if (activity != null) {
            loadKoinModules(financeModule)
            financeDetailAdapter = FinanceDetailAdapter()
            financeTypeAdapter = FinanceTypeAdapter()
            filter = arrayListOf()

            setSwipeGesture()


            financeTypeAdapter.onItemClick = {
                if (filter.contains(it))
                    filter.remove(it)
                else
                    filter.add(it)
                Log.d("Filter", filter.toString())
                loadFinanceDetail()
            }

            // Finance Detail Section
            binding?.financeFb?.setOnClickListener {
                findNavController().navigate(com.roviery.catetin.R.id.action_financeFragment_to_financeDetailDialogFragment)
            }
            financeDetailAdapter.onItemClick = {
                val toFinanceDetailDialog =
                    FinanceFragmentDirections.actionFinanceFragmentToFinanceDetailDialogFragment(it)
                findNavController().navigate(toFinanceDetailDialog)
            }

            loadMonthlyBudget()
            loadFinanceType()
            loadFinanceDetail()
        }
    }

    private fun setSwipeGesture() {
        val swipeGesture = object : SwipeGesture(requireActivity()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val financeDetail = financeDetailAdapter.getData(viewHolder.adapterPosition)
                val type = financeDetail.financeDetailType
                val finance = financeViewModel.getFinanceByType(type)
                when (direction) {
                    ItemTouchHelper.LEFT -> {
                        // update finance
                        financeViewModel.updateFinance(
                            finance,
                            finance.financeType,
                            finance.financeFundAllocation,
                            finance.financeUsedFund - financeDetail.financeDetailExpense,
                            finance.financeRemainingFund + financeDetail.financeDetailExpense
                        )
                        //delete finance
                        financeViewModel.deleteFinanceDetail(financeDetail)
                    }

                    ItemTouchHelper.RIGHT -> {
                        // update finance
                        financeViewModel.updateFinance(
                            finance,
                            finance.financeType,
                            finance.financeFundAllocation,
                            finance.financeUsedFund - financeDetail.financeDetailExpense,
                            finance.financeRemainingFund + financeDetail.financeDetailExpense
                        )
                        //delete finance
                        financeViewModel.deleteFinanceDetail(financeDetail)
                    }
                }
            }
        }
        val touchHelper = ItemTouchHelper(swipeGesture)
        touchHelper.attachToRecyclerView(binding?.financeDetailRv)
    }

    private fun loadMonthlyBudget() {
        financeViewModel.getFinanceBudget().observe(viewLifecycleOwner){ data ->
            Log.d("Budget", data.toString())
            if (data != null){
                val budget = NumberFormat.getInstance(Locale.GERMAN).format(data)
                binding?.tvBudgetRemaining?.text = "Rp$budget"
            }
            else{
                binding?.tvBudgetRemaining?.text = "-"
                binding?.tvTotalBudget?.text = "/-"
            }
        }
    }

    private fun loadFinanceType() {
        financeViewModel.listFinanceType.observe(viewLifecycleOwner) { financeType ->
            Log.d("Finance Type", financeType.toString())
            financeTypeAdapter.setData(financeType)
            with(binding?.financeTypeRv) {
                this?.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                this?.setHasFixedSize(true)
                this?.adapter = financeTypeAdapter
            }
        }
    }

    private fun loadFinanceDetail() {
        financeViewModel.getFinanceDetailByType(filter)
            .observe(viewLifecycleOwner) { financeDetail ->
                Log.d("Finance Detail List", financeDetail.toString())
                binding?.noDataPreview?.visibility =
                    if (financeDetail.isEmpty()) View.VISIBLE else View.GONE
                financeDetailAdapter.setData(financeDetail)
            }

        with(binding?.financeDetailRv) {
            this?.layoutManager = LinearLayoutManager(context)
            this?.setHasFixedSize(true)
            this?.adapter = financeDetailAdapter
        }
    }

}