package com.roviery.todo

import android.os.Bundle
import android.text.TextUtils.isEmpty
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.roviery.catetin.R
import com.roviery.core.ui.TodoAdapter
import com.roviery.todo.databinding.FragmentTodoBinding
import com.roviery.todo.di.todoModule
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class TodoFragment : Fragment() {

    private val todoViewModel: TodoViewModel by viewModel()
    private var _binding: FragmentTodoBinding? = null
    private val binding get() = _binding

    private lateinit var todoAdapter: TodoAdapter
    private lateinit var inProgressAdapter: TodoAdapter
    private lateinit var doneAdapter: TodoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTodoBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            loadKoinModules(todoModule)
            todoAdapter = TodoAdapter()
            inProgressAdapter = TodoAdapter()
            doneAdapter = TodoAdapter()

            binding?.todoFb?.setOnClickListener {
                findNavController().navigate(R.id.action_todoFragment_to_todoDialogFragment)
            }

            todoAdapter.onItemLongClick = {
                todoViewModel.updateTodo(it, "in progress", it.todoMessage)
                Log.d("After Update", it.todoStatus)
            }
            inProgressAdapter.onItemLongClick = {
                todoViewModel.updateTodo(it, "done", it.todoMessage)
                Log.d("After Update", it.todoStatus)
            }
            doneAdapter.onItemLongClick = {
                todoViewModel.deleteTodo(it)
                Log.d("After Update", it.todoStatus)
            }

            todoAdapter.onItemClick = {
                val toTodoDialog =
                    TodoFragmentDirections.actionTodoFragmentToTodoDialogFragment(it)
                findNavController().navigate(toTodoDialog)
            }
            inProgressAdapter.onItemClick = {
                val toTodoDialog =
                    TodoFragmentDirections.actionTodoFragmentToTodoDialogFragment(it)
                findNavController().navigate(toTodoDialog)
            }
            doneAdapter.onItemClick = {
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
            binding?.todoEmptyTodo?.visibility = if (todo.isEmpty()) View.VISIBLE else View.GONE
            todoAdapter.setData(todo)
        }

        todoViewModel.inProgressList.observe(viewLifecycleOwner) { inProgress ->
            Log.d("In Progress List", inProgress.toString())
            binding?.todoEmptyInprogress?.visibility = if (inProgress.isEmpty()) View.VISIBLE else View.GONE
            inProgressAdapter.setData(inProgress)
        }

        todoViewModel.doneList.observe(viewLifecycleOwner) { done ->
            Log.d("Done", done.toString())
            binding?.todoEmptyDone?.visibility = if (done.isEmpty()) View.VISIBLE else View.GONE
            doneAdapter.setData(done)
        }

        with(binding?.todoRvTodo) {
            this?.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            this?.setHasFixedSize(true)
            this?.adapter = todoAdapter
        }

        with(binding?.todoRvInprogress) {
            this?.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            this?.setHasFixedSize(true)
            this?.adapter = inProgressAdapter
        }

        with(binding?.todoRvDone) {
            this?.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            this?.setHasFixedSize(true)
            this?.adapter = doneAdapter
        }
    }

}