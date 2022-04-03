package com.roviery.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.roviery.core.R
import com.roviery.core.databinding.DeadlineCardBinding
import com.roviery.core.domain.model.Deadline

class DeadlineAdapter : RecyclerView.Adapter<DeadlineAdapter.DeadlineViewHolder>() {

    private var listData = ArrayList<Deadline>()
    var onItemClick: ((Deadline) -> Unit)? = null
    var onItemLongClick: ((Deadline) -> Unit)? = null


    fun setData(newListData: List<Deadline>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeadlineViewHolder =
        DeadlineViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.deadline_card, parent, false)
        )

    override fun onBindViewHolder(holder: DeadlineViewHolder, position: Int) {
        val deadline = listData[position]
        holder.bind(deadline)
    }

    override fun getItemCount(): Int = listData.size

    inner class DeadlineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = DeadlineCardBinding.bind(itemView)
        fun bind(data: Deadline) {
            with(binding) {
                tvDeadlineDate.text = data.deadlineDate
                tvDeadlineNotes.text = data.deadlineNotes
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