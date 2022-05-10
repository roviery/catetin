package com.roviery.todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.roviery.todo.databinding.FragmentTodoDialogBinding

class TodoDialogFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentTodoDialogBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTodoDialogBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            binding?.dialogBtnSave?.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    companion object {
        const val TAG = "Todo Dialog"
    }

}