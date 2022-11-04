package com.roviery.todo.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.roviery.core.ui.TodoAdapter
import com.roviery.core.utils.SwipeGesture
import com.roviery.todo.TodoFragmentDirections
import com.roviery.todo.TodoViewModel
import com.roviery.todo.databinding.FragmentInProgressViewBinding
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
                todoViewModel.updateTodo(it, "Done", it.todoMessage)
            }

            inProgressAdapter.onItemClick = {
                val toTodoDialog =
                    TodoFragmentDirections.actionTodoFragmentToTodoDialogFragment(it)
                findNavController().navigate(toTodoDialog)
            }

            val swipeGesture = object : SwipeGesture(requireActivity()) {
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    when (direction) {
                        ItemTouchHelper.LEFT -> {
                            todoViewModel.updateTodo(
                                inProgressAdapter.getData(viewHolder.adapterPosition),
                                "Done",
                                inProgressAdapter.getData(viewHolder.adapterPosition).todoMessage
                            )
                        }
                        ItemTouchHelper.RIGHT -> {
                            todoViewModel.updateTodo(
                                inProgressAdapter.getData(viewHolder.adapterPosition),
                                "Done",
                                inProgressAdapter.getData(viewHolder.adapterPosition).todoMessage
                            )
                        }
                    }
                }
            }
            val touchHelper = ItemTouchHelper(swipeGesture)
            touchHelper.attachToRecyclerView(binding?.inprogressRv)

            loadInProgress()
        }
    }

    private fun loadInProgress() {
        todoViewModel.inProgressList.observe(viewLifecycleOwner) { inprogress ->
            binding?.emptyTodo?.visibility = if (inprogress.isEmpty()) View.VISIBLE else View.GONE
            inProgressAdapter.setData(inprogress)
        }

        with(binding?.inprogressRv) {
            this?.layoutManager = LinearLayoutManager(requireContext())
            this?.setHasFixedSize(true)
            this?.adapter = inProgressAdapter
        }
    }

}