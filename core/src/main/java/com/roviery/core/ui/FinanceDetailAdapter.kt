package com.roviery.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.roviery.core.R
import com.roviery.core.databinding.FinanceDetailCardBinding
import com.roviery.core.domain.model.FinanceDetail
import java.text.NumberFormat
import java.util.*

class FinanceDetailAdapter : RecyclerView.Adapter<FinanceDetailAdapter.FinanceDetailViewHolder>() {

    private var listData = ArrayList<FinanceDetail>()
    var onItemClick: ((FinanceDetail) -> Unit)? = null

    fun setData(newListData: List<FinanceDetail>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    fun getData(position: Int): FinanceDetail = listData[position]

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FinanceDetailAdapter.FinanceDetailViewHolder =
        FinanceDetailViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.finance_detail_card, parent, false)
        )

    override fun onBindViewHolder(
        holder: FinanceDetailAdapter.FinanceDetailViewHolder,
        position: Int
    ) {
        val financeDetail = listData[position]
        holder.bind(financeDetail)
    }

    override fun getItemCount(): Int = listData.size

    inner class FinanceDetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = FinanceDetailCardBinding.bind(itemView)
        fun bind(data: FinanceDetail) {
            val expense = NumberFormat.getInstance(Locale.GERMAN).format(data.financeDetailExpense)

            with(binding) {
                financeDetailExpense.text = "Rp$expense"
                financeDetailType.text = data.financeDetailType
                financeDetailName.text = data.financeDetailName
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }

}