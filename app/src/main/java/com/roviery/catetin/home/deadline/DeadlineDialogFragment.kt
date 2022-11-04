package com.roviery.catetin.home.deadline

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.roviery.catetin.databinding.FragmentDeadlineDialogBinding
import com.roviery.catetin.home.HomeViewModel
import com.roviery.core.domain.model.Deadline
import com.roviery.core.utils.DateConverter.stringMonth
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

    private var savedDay = "Monday"             // "Monday", "Tuesday", "Wednesday" ...
    private var savedDayOfMonth = "0"           // 1-28 / 1-29 / 1-30 / 1-31
    private var savedMonthString = "January"    // "January", "February", "March" ...
    private var savedMonth = "0"                  // 0 - 11
    private var savedYear = 0                   // "2020", "2021", "2022" ...

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
                val dateSplit: List<String> = deadline.deadlineDate.split("-")

                val simpleDateFormat = SimpleDateFormat("EEEE")
                val date =
                    Date(dateSplit[2].toInt(), dateSplit[0].toInt(), dateSplit[1].toInt() - 1)
                savedDay = simpleDateFormat.format(date)
                savedDayOfMonth =
                    if (dateSplit[1].toInt() < 10) "0${dateSplit[1].toInt()}" else dateSplit[1]
                savedMonth =
                    if (dateSplit[0].toInt() < 10) "0${dateSplit[0].toInt()}" else dateSplit[0]
                savedMonthString = stringMonth(dateSplit[0].toInt())
                savedYear = dateSplit[2].toInt()

                val datePreview =
                    "$savedDay, ${savedDayOfMonth.toInt()} $savedMonthString $savedYear"
                binding?.dialogTvDate?.text = datePreview
                binding?.dialogEtKeterangan?.setText(deadline.deadlineNotes)
            } else {
                getDateCalendar()
                binding?.dialogTvDate?.text =
                    "$savedDay, ${savedDayOfMonth.toInt()} $savedMonthString $savedYear"
            }

            binding?.dialogIbCloseDeadline?.setOnClickListener {
                findNavController().navigateUp()
            }

            binding?.dialogBtnDate?.setOnClickListener {
                if (deadline != null) {
                    DatePickerDialog(
                        requireContext(),
                        this,
                        savedYear,
                        savedMonth.toInt(),
                        savedDayOfMonth.toInt()
                    ).show()
                } else {
                    getDateCalendar()
                    DatePickerDialog(
                        requireContext(),
                        this,
                        year,
                        month,
                        dayOfMonth
                    ).show()
                }
            }

            binding?.dialogBtnSave?.setOnClickListener {
                val notes = binding?.dialogEtKeterangan?.text.toString()

                if (notes.isNotEmpty()) {
                    if (deadline != null) {
                        homeViewModel.updateDeadline(
                            deadline, "$savedMonth-$savedDayOfMonth-$savedYear",
                            binding?.dialogEtKeterangan?.text.toString()
                        )
                        dialog?.dismiss()
                    } else {
                        val newDeadline = Deadline(
                            0,
                            "$savedMonth-$savedDayOfMonth-$savedYear",
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
        savedDayOfMonth = if (dayOfMonth < 10) "0$dayOfMonth" else "$dayOfMonth"
        savedMonth = if (month < 10) "0$month" else "$month"
        savedMonthString = stringMonth(month)
        savedYear = year

        binding?.dialogTvDate?.text =
            "$savedDay, ${savedDayOfMonth.toInt()} $savedMonthString $savedYear"
    }

    private fun getDateCalendar() {
        val cal = Calendar.getInstance()
        dayOfMonth = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)

        val simpleDateFormat = SimpleDateFormat("EEEE")
        val date = Date(year, month, dayOfMonth - 1)
        savedDay = simpleDateFormat.format(date)
        savedDayOfMonth = if (dayOfMonth < 10) "0$dayOfMonth" else "$dayOfMonth"
        savedMonth = if (month < 10) "0$month" else "$month"
        savedMonthString = stringMonth(month)
        savedYear = year
    }


}