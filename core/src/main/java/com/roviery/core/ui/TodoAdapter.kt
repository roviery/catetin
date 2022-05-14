package com.roviery.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.roviery.core.R
import com.roviery.core.databinding.TodoCardBinding
import com.roviery.core.domain.model.Todo

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    private var listData = ArrayList<Todo>()
    var onItemClick: ((Todo) -> Unit)? = null
    var onItemLongClick: ((Todo) -> Unit)? = null

    fun setData(newListData: List<Todo>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder =
        TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.todo_card, parent, false)
        )

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = listData[position]
        holder.bind(todo)
    }

    override fun getItemCount(): Int = listData.size

    inner class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = TodoCardBinding.bind(itemView)
        fun bind(data: Todo) {
            with(binding) {
                tvTodo.text = data.todoMessage
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
            binding.root.setOnLongClickListener {
                onItemLongClick?.invoke(listData[adapterPosition])
                true
            }
        }
    }
}