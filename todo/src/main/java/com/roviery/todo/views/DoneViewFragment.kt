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
import com.roviery.todo.databinding.FragmentDoneViewBinding
import com.roviery.todo.di.todoModule
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class DoneViewFragment : Fragment() {

    private val todoViewModel: TodoViewModel by viewModel()
    private var _binding: FragmentDoneViewBinding? = null
    private val binding get() = _binding

    private lateinit var doneAdapter: TodoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDoneViewBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            loadKoinModules(todoModule)
            doneAdapter = TodoAdapter()

            doneAdapter.onItemLongClick = {
                todoViewModel.deleteTodo(it)
            }

            doneAdapter.onItemClick = {
                val toTodoDialog =
                    TodoFragmentDirections.actionTodoFragmentToTodoDialogFragment(it)
                findNavController().navigate(toTodoDialog)
            }

            loadDone()
        }

    }

    private fun loadDone() {
        todoViewModel.doneList.observe(viewLifecycleOwner) { done ->
            Log.d("Todo List", done.toString())
            binding?.emptyTodo?.visibility = if (done.isEmpty()) View.VISIBLE else View.GONE
            doneAdapter.setData(done)
        }

        with(binding?.doneRv) {
            this?.layoutManager = GridLayoutManager(requireContext(), 2)
            this?.setHasFixedSize(true)
            this?.adapter = doneAdapter
        }
    }
}