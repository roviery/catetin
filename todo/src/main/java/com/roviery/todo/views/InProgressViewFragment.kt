package com.roviery.todo.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.roviery.core.ui.TodoAdapter
import com.roviery.todo.R
import com.roviery.todo.TodoFragmentDirections
import com.roviery.todo.TodoViewModel
import com.roviery.todo.databinding.FragmentInProgressViewBinding
import com.roviery.todo.databinding.FragmentTodoViewBinding
import com.roviery.todo.di.todoModule
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class InProgressViewFragment : Fragment() {

    private val todoViewModel: TodoViewModel by viewModel()
    private var _binding: FragmentInProgressViewBinding? = null
    private val binding get() = _binding

    private lateinit var inProgressAdapter: TodoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInProgressViewBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            loadKoinModules(todoModule)
            inProgressAdapter = TodoAdapter()

            inProgressAdapter.onItemLongClick = {
                todoViewModel.updateTodo(it, "done", it.todoMessage)
                Log.d("After Update", it.todoStatus)
            }

            inProgressAdapter.onItemClick = {
                val toTodoDialog =
                    TodoFragmentDirections.actionTodoFragmentToTodoDialogFragment(it)
                findNavController().navigate(toTodoDialog)
            }

            loadInProgress()
        }
    }

    private fun loadInProgress() {
        todoViewModel.inProgressList.observe(viewLifecycleOwner) { todo ->
            Log.d("Todo List", todo.toString())
            inProgressAdapter.setData(todo)
        }

        with(binding?.inprogressRv) {
            this?.layoutManager = GridLayoutManager(requireContext(), 2)
            this?.setHasFixedSize(true)
            this?.adapter = inProgressAdapter
        }
    }

}