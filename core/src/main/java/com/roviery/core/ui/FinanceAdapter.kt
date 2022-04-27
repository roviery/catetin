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
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

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
            val fundAllocation = NumberFormat.getInstance(Locale.GERMAN).format(data.financeFundAllocation)
            val usedFund = NumberFormat.getInstance(Locale.GERMAN).format(data.financeUsedFund)
            val remainingFund = NumberFormat.getInstance(Locale.GERMAN).format(data.financeRemainingFund)

            with(binding) {
                if (data.financeUsedFund > 0.8*data.financeFundAllocation)
                    binding.tvTitle.setBackgroundResource(R.color.finance_red)
                else if (data.financeUsedFund > 0.5*data.financeFundAllocation)
                    binding.tvTitle.setBackgroundResource(R.color.primary_orange)

                tvTitle.text = data.financeType
                fundAllocationNominal.text = "Rp$fundAllocation"
                usedFundNominal.text = "Rp$usedFund"
                remainingFundNominal.text = "Rp$remainingFund"
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