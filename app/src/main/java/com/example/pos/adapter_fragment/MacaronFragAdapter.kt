package com.example.pos.adapter_fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pos.R
import com.example.pos.product.Macaron

class MacaronFragAdapter(private val mList: List<Macaron>, private val onItemClicked: (Macaron) -> Unit) : RecyclerView.Adapter<MacaronFragAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val macaronView = inflater.inflate(R.layout.item_list_product_onlyname, parent, false)

        return ViewHolder(macaronView) {
            onItemClicked(mList[it])
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val macaron: Macaron = mList[position]

        val nameText = holder.productNameView

        nameText.setText(macaron.name)
    }

    //return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    inner class ViewHolder(ItemView: View, onItemClickedFunction: (Int) -> Unit) : RecyclerView.ViewHolder(ItemView) {

        init {
            ItemView.setOnClickListener {
                onItemClickedFunction(adapterPosition)
            }
        }

        val productNameView: TextView = ItemView.findViewById(R.id.pName)
    }
}