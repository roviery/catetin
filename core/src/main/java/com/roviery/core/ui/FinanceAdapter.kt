package com.roviery.core.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.roviery.core.R
import com.roviery.core.databinding.FinanceCardBinding
import com.roviery.core.domain.model.Finance
import java.text.NumberFormat
import java.util.*

class FinanceAdapter(private val context: Context) :
    RecyclerView.Adapter<FinanceAdapter.FinanceViewHolder>() {

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
            val fundAllocation =
                NumberFormat.getInstance(Locale.GERMAN).format(data.financeFundAllocation)
            val usedFund = NumberFormat.getInstance(Locale.GERMAN).format(data.financeUsedFund)
            val remainingFund =
                NumberFormat.getInstance(Locale.GERMAN).format(data.financeRemainingFund)

            with(binding) {
                tvTitle.text = data.financeType

                if (data.financeType.contains("Food", ignoreCase = true)) {
                    iconCard.setCardBackgroundColor(
                        ContextCompat.getColor(
                            context,
                            R.color.finance_red
                        )
                    )
                    iconImg.setImageResource(R.drawable.ic_food)
                } else if (data.financeType.contains("Transport", ignoreCase = true)) {
                    iconCard.setCardBackgroundColor(
                        ContextCompat.getColor(
                            context,
                            R.color.finance_green
                        )
                    )
                    iconImg.setImageResource(R.drawable.ic_transportation)
                } else if (data.financeType.contains("Electric", ignoreCase = true)) {
                    iconCard.setCardBackgroundColor(
                        ContextCompat.getColor(
                            context,
                            R.color.finance_yellow
                        )
                    )
                    iconImg.setImageResource(R.drawable.ic_electricity)
                } else {
                    iconCard.setCardBackgroundColor(
                        ContextCompat.getColor(
                            context,
                            R.color.gray
                        )
                    )
                    iconImg.setImageResource(R.drawable.ic_money)
                }

                tvFundAllocation.text = "Rp$fundAllocation"
                tvRemainingFund.text = "Remaining Rp$remainingFund"
                tvUsedFund.text = "Used Rp$usedFund"
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