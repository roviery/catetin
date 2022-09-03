package com.roviery.catetin.home.deadline

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.DatePicker
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.roviery.catetin.databinding.FragmentDeadlineDialogBinding
import com.roviery.catetin.home.HomeViewModel
import com.roviery.core.domain.model.Deadline
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class DeadlineDialogFragment : BottomSheetDialogFragment(), DatePickerDialog.OnDateSetListener {

    private val homeViewModel: HomeViewModel by viewModel()
    private var _binding: FragmentDeadlineDialogBinding? = null
    private val binding get() = _binding

    private var dayOfMonth = 0
    private var month = 0
    private var year = 0
    private var savedDay = "Senin"
    private var savedDayOfMonth = 0
    private var savedMonthString = "Senin"
    private var savedMonth = 0
    private var savedYear = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDeadlineDialogBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val deadline = DeadlineDialogFragmentArgs.fromBundle(arguments as Bundle).deadline

            if (deadline != null) {
                binding?.dialogTvDate?.text = deadline.deadlineDate
                binding?.dialogEtKeterangan?.setText(deadline.deadlineNotes)
            }

            getDateCalendar()
            binding?.dialogTvDate?.text = "$savedDay, $savedDayOfMonth $savedMonthString $savedYear"

            binding?.dialogIbCloseDeadline?.setOnClickListener {
                findNavController().navigateUp()
            }

            binding?.dialogBtnDate?.setOnClickListener {
                getDateCalendar()
                DatePickerDialog(requireContext(), this, year, month, dayOfMonth).show()
            }

            binding?.dialogBtnSave?.setOnClickListener {
                val notes = binding?.dialogEtKeterangan?.text.toString()

                if (notes.isNotEmpty()) {
                    if (deadline != null) {
                        homeViewModel.updateDeadline(
                            deadline, binding?.dialogTvDate?.text.toString(),
                            binding?.dialogEtKeterangan?.text.toString()
                        )
                        dialog?.dismiss()
                    } else {
                        val newDeadline = Deadline(
                            0,
                            binding?.dialogTvDate?.text.toString(),
                            binding?.dialogEtKeterangan?.text.toString()
                        )
                        homeViewModel.insertDeadline(newDeadline)
                        dialog?.dismiss()
                    }
                } else {
                    Toast.makeText(requireContext(), "Invalid Data", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val simpleDateFormat = SimpleDateFormat("EEEE")
        val date = Date(year, month, dayOfMonth - 1)

        savedDay = simpleDateFormat.format(date)
        savedDayOfMonth = dayOfMonth
        savedMonth = month
        savedYear = year

        when (savedMonth) {
            0 -> savedMonthString = "January"
            1 -> savedMonthString = "February"
            2 -> savedMonthString = "March"
            3 -> savedMonthString = "April"
            4 -> savedMonthString = "May"
            5 -> savedMonthString = "June"
            6 -> savedMonthString = "July"
            7 -> savedMonthString = "August"
            8 -> savedMonthString = "September"
            9 -> savedMonthString = "October"
            10 -> savedMonthString = "November"
            11 -> savedMonthString = "December"
        }

        binding?.dialogTvDate?.text = "$savedDay, $savedDayOfMonth $savedMonthString $savedYear"
    }

    private fun getDateCalendar() {
        val cal = Calendar.getInstance()
        dayOfMonth = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)

        val simpleDateFormat = SimpleDateFormat("EEEE")
        val date = Date(year, month, dayOfMonth - 1)
        savedDay = simpleDateFormat.format(date)
        savedDayOfMonth = dayOfMonth
        savedMonth = month
        savedYear = year

        when (savedMonth) {
            0 -> savedMonthString = "January"
            1 -> savedMonthString = "February"
            2 -> savedMonthString = "March"
            3 -> savedMonthString = "April"
            4 -> savedMonthString = "May"
            5 -> savedMonthString = "June"
            6 -> savedMonthString = "July"
            7 -> savedMonthString = "August"
            8 -> savedMonthString = "September"
            9 -> savedMonthString = "October"
            10 -> savedMonthString = "November"
            11 -> savedMonthString = "December"
        }
    }

}