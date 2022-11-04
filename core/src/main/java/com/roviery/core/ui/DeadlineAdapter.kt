package com.roviery.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.roviery.core.R
import com.roviery.core.databinding.DeadlineCardBinding
import com.roviery.core.domain.model.Deadline
import com.roviery.core.utils.DateConverter
import java.text.SimpleDateFormat
import java.util.*

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
                val dateSplit = data.deadlineDate.split("-")

                val simpleDateFormat = SimpleDateFormat("EEEE")
                val date =
                    Date(dateSplit[2].toInt(), dateSplit[0].toInt(), dateSplit[1].toInt() - 1)
                val savedDay = simpleDateFormat.format(date)
                val savedDayOfMonth = dateSplit[1].toInt()
                val savedMonthString = DateConverter.stringMonth(dateSplit[0].toInt())
                val savedYear = dateSplit[2].toInt()

                val datePreview = "$savedDay, $savedDayOfMonth $savedMonthString $savedYear"
                tvDeadlineDate.text = datePreview
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