package com.roviery.core.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.roviery.core.R
import com.roviery.core.databinding.FinanceTypeBinding
import com.roviery.core.ui.FinanceTypeAdapter.FinanceTypeViewHolder

class FinanceTypeAdapter() : RecyclerView.Adapter<FinanceTypeViewHolder>() {

    private var listData = ArrayList<String>()
    var onItemClick: ((String) -> Unit)? = null
    var filter: MutableList<String> = arrayListOf()

    fun setData(newListData: List<String>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FinanceTypeViewHolder =
        FinanceTypeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.finance_type, parent, false)
        )

    override fun onBindViewHolder(holder: FinanceTypeViewHolder, position: Int) {
        val financeType = listData[position]
        holder.bind(financeType)
    }

    override fun getItemCount(): Int = listData.size

    inner class FinanceTypeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = FinanceTypeBinding.bind(itemView)

        fun bind(data: String) {
            with(binding) {
                typeTv.text = data
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])

                with(binding) {
                    if (filter.contains(typeTv.text.toString())) {
                        filter.remove(typeTv.text.toString())
                        typeCard.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
                        typeTv.setTextColor(Color.parseColor("#FF000000"))
                    } else {
                        filter.add(typeTv.text.toString())
                        typeCard.setCardBackgroundColor(Color.parseColor("#EFA331"))
                        typeTv.setTextColor(Color.parseColor("#FFFFFF"))
                    }
                }
            }
        }
    }
}