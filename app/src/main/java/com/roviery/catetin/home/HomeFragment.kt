package com.roviery.catetin.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.roviery.catetin.R
import com.roviery.catetin.databinding.FragmentHomeBinding
import com.roviery.core.domain.model.Deadline
import com.roviery.core.ui.DeadlineAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var deadlineAdapter: DeadlineAdapter

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

            binding?.homeIbDeadline?.setOnClickListener {
                showDeadlineDialog()
            }

            deadlineAdapter.onItemClick = {
            }

            deadlineAdapter.onItemLongClick = {
                showDeleteDialog(it)
            }
        }

        loadDeadline()
    }

    private fun loadDeadline() {
        homeViewModel.listDeadline.observe(viewLifecycleOwner) { deadline ->
            Log.d("Deadline List", deadline.toString())
            if (deadline.isEmpty()){
                binding?.homeTvEmptyDeadline?.visibility = View.VISIBLE
                deadlineAdapter.setData(deadline)
            }
            else {
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

    private fun showDeadlineDialog() {
        findNavController().navigate(R.id.action_homeFragment_to_deadlineDialogFragment)
    }

    private fun showDeleteDialog(deadline: Deadline) {
        val toDeleteDialogFragment = HomeFragmentDirections.actionHomeFragmentToDeleteDialogFragment(deadline)
        findNavController().navigate(toDeleteDialogFragment)
    }

}