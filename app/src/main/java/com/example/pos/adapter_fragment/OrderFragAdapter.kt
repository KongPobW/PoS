package com.example.pos.adapter_fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.pos.R
import com.example.pos.database.DataOrder
import kotlinx.android.synthetic.main.item_order.view.*

class OrderFragAdapter(val orderData: List<DataOrder>) : RecyclerView.Adapter<OrderFragAdapter.ViewHolder>() {

    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val linearLayout = ItemView.findViewById(R.id.linear_order) as LinearLayout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.apply {
            userID.text = orderData[position].uid.toString()
            branID.text = orderData[position].bid.toString()
            stafID.text = orderData[position].sid.toString()
            creAt.text = orderData[position].createAt.toString()
            updAt.text = orderData[position].updateAt.toString()
        }

        holder.linearLayout.setOnClickListener {
            it.findNavController().navigate(R.id.orderlinefrag)
        }
    }

    override fun getItemCount(): Int {
        return orderData.size
    }
}