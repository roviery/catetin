package com.roviery.todo.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.roviery.core.ui.TodoAdapter
import com.roviery.todo.TodoFragmentDirections
import com.roviery.todo.TodoViewModel
import com.roviery.todo.databinding.FragmentTodoViewBinding
import com.roviery.todo.di.todoModule
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class TodoViewFragment : Fragment() {

    private val todoViewModel: TodoViewModel by viewModel()
    private var _binding: FragmentTodoViewBinding? = null
    private val binding get() = _binding

    private lateinit var todoAdapter: TodoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTodoViewBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            loadKoinModules(todoModule)
            todoAdapter = TodoAdapter()

            todoAdapter.onItemLongClick = {
                todoViewModel.updateTodo(it, "in progress", it.todoMessage)
                Log.d("After Update", it.todoStatus)
            }

            todoAdapter.onItemClick = {
                val toTodoDialog =
                    TodoFragmentDirections.actionTodoFragmentToTodoDialogFragment(it)
                findNavController().navigate(toTodoDialog)
            }

            loadTodo()
        }
    }

    private fun loadTodo() {
        todoViewModel.todoList.observe(viewLifecycleOwner) { todo ->
            Log.d("Todo List", todo.toString())
            binding?.emptyTodo?.visibility = if (todo.isEmpty()) View.VISIBLE else View.GONE
            todoAdapter.setData(todo)
        }

        with(binding?.todoRv) {
            this?.layoutManager = GridLayoutManager(requireContext(), 2)
            this?.setHasFixedSize(true)
            this?.adapter = todoAdapter
        }
    }
}