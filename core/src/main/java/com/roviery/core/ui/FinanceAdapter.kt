package com.roviery.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.roviery.core.R
import com.roviery.core.databinding.DeadlineCardBinding
import com.roviery.core.databinding.FinanceCardBinding
import com.roviery.core.domain.model.Deadline
import com.roviery.core.domain.model.Finance

class FinanceAdapter : RecyclerView.Adapter<FinanceAdapter.FinanceViewHolder>() {

    private var listData = ArrayList<Finance>()
    var onItemClick: ((Finance) -> Unit)? = null
    var onItemLongClick: ((Finance) -> Unit)? = null

    fun setData(newListData: List<Finance>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FinanceViewHolder =
        FinanceViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.finance_card, parent, false)
        )

    override fun onBindViewHolder(holder: FinanceViewHolder, position: Int) {
        val finance = listData[position]
        holder.bind(finance)
    }

    override fun getItemCount(): Int = listData.size

    inner class FinanceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = FinanceCardBinding.bind(itemView)
        fun bind(data: Finance) {
            with(binding) {
                tvTitle.text = data.financeType
                tvFundAllocation.text = "Rp${data.financeFundAllocation.toString()}"
                tvUsedFund.text = "Rp${data.financeUsedFund.toString()}"
                tvRemainingFund.text = "Rp${data.financeRemainingFund.toString()}"
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