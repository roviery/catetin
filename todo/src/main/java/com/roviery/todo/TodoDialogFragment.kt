package com.roviery.todo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.roviery.core.domain.model.Todo
import com.roviery.todo.databinding.FragmentTodoDialogBinding
import org.koin.android.viewmodel.ext.android.viewModel

class TodoDialogFragment : BottomSheetDialogFragment() {

    private val todoViewModel: TodoViewModel by viewModel()
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

            val todo = TodoDialogFragmentArgs.fromBundle(arguments as Bundle).todo

            if (todo != null) {
                binding?.todoDelete?.visibility = View.VISIBLE
                binding?.dialogEtTodo?.setText(todo.todoMessage)
            }

            binding?.dialogBtnSave?.setOnClickListener {
                val status = "todo"
                val message = binding?.dialogEtTodo?.text.toString()

                if (todo != null) {
                    todoViewModel.updateTodo(todo, todo.todoStatus, message)
                } else {
                    var newTodo = Todo(
                        0,
                        status,
                        message
                    )
                    Log.d("Insert todoMessage", message)
                    todoViewModel.insertTodo(newTodo)
                }


                findNavController().navigateUp()
            }

            binding?.todoDelete?.setOnClickListener {
                if (todo != null) todoViewModel.deleteTodo(todo)
                findNavController().navigateUp()
            }
        }
    }

}