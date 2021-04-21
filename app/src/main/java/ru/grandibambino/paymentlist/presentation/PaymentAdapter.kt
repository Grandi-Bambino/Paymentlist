package ru.grandibambino.paymentlist.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.grandibambino.paymentlist.R
import ru.grandibambino.paymentlist.data.Payment
import java.text.SimpleDateFormat
import java.util.*

class PaymentAdapter : RecyclerView.Adapter<PaymentAdapter.PaymentViewHolder>() {

    private var payments = mutableListOf<Payment>()

    fun setData(data: MutableList<Payment>) {
        payments = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PaymentViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.payment_item, parent, false)
        )

    override fun onBindViewHolder(holder: PaymentViewHolder, position: Int) {
        holder.initFields()
        holder.bind(payments[position])
    }

    override fun getItemCount() = payments.size

    class PaymentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private lateinit var desc: TextView
        private lateinit var amount: TextView
        private lateinit var currency: TextView
        private lateinit var created: TextView

        fun initFields() {
            desc = itemView.findViewById(R.id.desc_tv)
            amount = itemView.findViewById(R.id.amount_tv)
            currency = itemView.findViewById(R.id.currency_tv)
            created = itemView.findViewById(R.id.created_tv)
        }

        fun bind(payment: Payment) {
            desc.text = payment.desc
            amount.text = payment.amount
            currency.text = payment.currency
            created.text = data(payment.created)
        }

        private fun data(ms: String): String {
            val formatter = SimpleDateFormat(itemView.context.getString(R.string.formater_date),
                Locale.getDefault()
            )
            return formatter.format(Date(ms.toLong()))
        }

    }
}
