package com.roviery.catetin.home

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
import com.roviery.catetin.R
import com.roviery.catetin.databinding.FragmentHomeBinding
import com.roviery.core.ui.DeadlineAdapter
import com.roviery.core.ui.FinanceAdapter
import com.roviery.core.ui.QuicknotesAdapter
import com.roviery.core.utils.SwipeGesture
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var deadlineAdapter: DeadlineAdapter
    private lateinit var quicknotesAdapter: QuicknotesAdapter
    private lateinit var financeAdapter: FinanceAdapter

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            deadlineAdapter = DeadlineAdapter()
            quicknotesAdapter = QuicknotesAdapter()
            financeAdapter = FinanceAdapter()

            val swipeGesture = object : SwipeGesture(requireActivity()) {
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    when (direction) {
                        ItemTouchHelper.LEFT -> {
                            homeViewModel.deleteQuicknotes(quicknotesAdapter.getData(viewHolder.adapterPosition))
                        }

                        ItemTouchHelper.RIGHT -> {
                            homeViewModel.deleteQuicknotes(quicknotesAdapter.getData(viewHolder.adapterPosition))
                        }
                    }
                }
            }
            val touchHelper = ItemTouchHelper(swipeGesture)
            touchHelper.attachToRecyclerView(binding?.homeRvQuicknotes)

            // Deadline Section
            binding?.homeIbDeadline?.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_deadlineDialogFragment)
            }
            deadlineAdapter.onItemClick = {
                val toDeadlineDialog =
                    HomeFragmentDirections.actionHomeFragmentToDeadlineDialogFragment(it)
                findNavController().navigate(toDeadlineDialog)
            }
            deadlineAdapter.onItemLongClick = {
                val toDeleteDialogFragment =
                    HomeFragmentDirections.actionHomeFragmentToDeleteDialogFragment(deadline = it)
                findNavController().navigate(toDeleteDialogFragment)
            }

            // Quicknotes Section
            binding?.homeIbQuicknotes?.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_quicknotesDialogFragment)
            }
            quicknotesAdapter.onItemClick = {
                val toQuicknotesDialog =
                    HomeFragmentDirections.actionHomeFragmentToQuicknotesDialogFragment(it)
                findNavController().navigate(toQuicknotesDialog)
            }
            quicknotesAdapter.onItemLongClick = {
                val toDeleteDialogFragment =
                    HomeFragmentDirections.actionHomeFragmentToDeleteDialogFragment(quicknotes = it)
                findNavController().navigate(toDeleteDialogFragment)
            }

            // Financial Section
            binding?.homeIbFinancial?.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_financeDialogFragment)
            }
            financeAdapter.onItemClick = {
                val toFinanceDialog =
                    HomeFragmentDirections.actionHomeFragmentToFinanceDialogFragment(it)
                findNavController().navigate(toFinanceDialog)
            }
            financeAdapter.onItemLongClick = {
                val toDeleteDialogFragment =
                    HomeFragmentDirections.actionHomeFragmentToDeleteDialogFragment(finance = it)
                findNavController().navigate(toDeleteDialogFragment)
            }

            loadDeadline()
            loadQuicknotes()
            loadFinance()
        }
    }

    private fun loadDeadline() {
        homeViewModel.listDeadline.observe(viewLifecycleOwner) { deadline ->
            Log.d("Deadline List", deadline.toString())
            if (deadline.isEmpty()) {
                binding?.homeTvEmptyDeadline?.visibility = View.VISIBLE
                deadlineAdapter.setData(deadline)
            } else {
                binding?.homeTvEmptyDeadline?.visibility = View.GONE
                deadlineAdapter.setData(deadline)
            }
        }

        with(binding?.homeRvDeadline) {
            this?.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            this?.setHasFixedSize(true)
            this?.adapter = deadlineAdapter
        }
    }

    private fun loadQuicknotes() {
        homeViewModel.listQuicknotes.observe(viewLifecycleOwner) { quicknotes ->
            Log.d("Quicknotes List", quicknotes.toString())
            quicknotesAdapter.setData(quicknotes)
        }

        with(binding?.homeRvQuicknotes) {
            this?.layoutManager = LinearLayoutManager(context)
            this?.setHasFixedSize(true)
            this?.adapter = quicknotesAdapter
        }
    }

    private fun loadFinance() {
        homeViewModel.listFinance.observe(viewLifecycleOwner) { finance ->
            Log.d("Finance List", finance.toString())
            financeAdapter.setData(finance)
        }

        with(binding?.homeRvFinancial) {
            this?.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            this?.setHasFixedSize(true)
            this?.adapter = financeAdapter
        }
    }

}