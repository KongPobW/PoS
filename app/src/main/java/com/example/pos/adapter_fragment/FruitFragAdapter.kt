package com.example.pos.adapter_fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pos.R
import com.example.pos.product.Fruit

class FruitFragAdapter(private val fList: List<Fruit>, private val onItemClicked: (Fruit) -> Unit) : RecyclerView.Adapter<FruitFragAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val fruitView = inflater.inflate(R.layout.item_list_product_onlyname, parent, false)

        return ViewHolder(fruitView) {
            onItemClicked(fList[it])
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fruit: Fruit = fList[position]

        val nameText = holder.productNameView

        nameText.setText(fruit.name)
    }

    //return the number of the items in the list
    override fun getItemCount(): Int {
        return fList.size
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