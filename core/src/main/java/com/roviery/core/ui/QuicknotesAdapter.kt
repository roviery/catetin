package com.roviery.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.roviery.core.R
import com.roviery.core.databinding.QuicknotesCardBinding
import com.roviery.core.domain.model.Quicknotes

class QuicknotesAdapter : RecyclerView.Adapter<QuicknotesAdapter.QuicknotesViewHolder>() {

    private var listData = ArrayList<Quicknotes>()
    var onItemClick: ((Quicknotes) -> Unit)? = null
    var onItemLongClick: ((Quicknotes) -> Unit)? = null

    fun setData(newListData: List<Quicknotes>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    fun getData(position: Int): Quicknotes = listData[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuicknotesViewHolder =
        QuicknotesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.quicknotes_card, parent, false)
        )

    override fun onBindViewHolder(holder: QuicknotesViewHolder, position: Int) {
        val quicknotes = listData[position]
        holder.bind(quicknotes)
    }

    override fun getItemCount(): Int = listData.size

    inner class QuicknotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = QuicknotesCardBinding.bind(itemView)
        fun bind(data: Quicknotes) {
            with(binding) {
                tvQuicknotesText.text = data.quicknotesText
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