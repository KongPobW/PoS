package com.example.pos.adapter_fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.pos.R
import com.example.pos.database.DataOrderLine
import com.example.pos.fragment.order.OrderLineFragment
import kotlinx.android.synthetic.main.item_orderline.view.*

class OrderLineFragAdapter(var orderLineData: List<DataOrderLine>, val onItemClicked: (DataOrderLine) -> Unit) : RecyclerView.Adapter<OrderLineFragAdapter.ViewHolder>() {

    inner class ViewHolder(ItemView: View, onItemClickedFunction: (Int) -> Unit) : RecyclerView.ViewHolder(ItemView) {
        val delBtn = ItemView.findViewById(R.id.delete_item) as Button
        val updBtn = ItemView.findViewById(R.id.update_item) as Button
        val linearLayout = ItemView.findViewById(R.id.linear_orderline) as LinearLayout

        init {
            delBtn.setOnClickListener {
                OrderLineFragment.state = "DeleteButton"
                onItemClickedFunction(adapterPosition)
            }

            updBtn.setOnClickListener {
                OrderLineFragment.state = "UpdateButton"
                onItemClickedFunction(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_orderline, parent, false)
        return ViewHolder(view) {
            onItemClicked(orderLineData[it])
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.apply {
            orderID.text = orderLineData[position].oid.toString()
            prodName.text = orderLineData[position].pname
            qty.text = orderLineData[position].qty.toString()
            subtotal.text = orderLineData[position].stotal.toString()
        }

        holder.linearLayout.setOnClickListener {
            it.findNavController().navigate(R.id.orderfrag)
        }
    }

    override fun getItemCount(): Int {
        return orderLineData.size
    }
}