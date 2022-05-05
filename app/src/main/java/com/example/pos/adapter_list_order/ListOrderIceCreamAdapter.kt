package com.example.pos.adapter_list_order

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pos.R
import com.example.pos.product.IceCream

class ListOrderIceCreamAdapter(private val orderList: List<IceCream>) : RecyclerView.Adapter<ListOrderIceCreamAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListOrderIceCreamAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val listOrderView = inflater.inflate(R.layout.item_list_order, parent, false)

        return ViewHolder(listOrderView)
    }

    override fun onBindViewHolder(holder: ListOrderIceCreamAdapter.ViewHolder, position: Int) {
        val listOrder: IceCream = orderList[position]

        val idText = holder.productIDView
        val nameText = holder.productNameView
        val quantityText = holder.productQuantityView
        val sTotalText = holder.productSubTotalView

        idText.setText(listOrder.id.toString())
        nameText.setText(listOrder.name)
        quantityText.setText(listOrder.quantity.toString())
        sTotalText.setText(listOrder.sTotal.toString())
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val productIDView: TextView = ItemView.findViewById(R.id.id_of_product)
        val productNameView: TextView = ItemView.findViewById(R.id.name_product)
        val productQuantityView: TextView = ItemView.findViewById(R.id.quantity_product)
        val productSubTotalView: TextView = ItemView.findViewById(R.id.subtotal_product)
    }
}