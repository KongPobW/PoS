package com.example.pos.adapter_product

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pos.R
import com.example.pos.activity.OrderActivity
import com.example.pos.product.Drink
import com.example.pos.product.Macaron

class DrinkAdapter(private val dList: List<Drink>, private val onItemClicked: (Drink) -> Unit) : RecyclerView.Adapter<DrinkAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val drinkView = inflater.inflate(R.layout.item_list_product, parent, false)

        return ViewHolder(drinkView) {
            onItemClicked(dList[it])
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val drink: Drink = dList[position]

        val imageView = holder.productImageView
        val nameText = holder.productNameView
        val priceText = holder.productPriceView

        when (position) {
            0 -> imageView.setImageResource(R.drawable.water)
            1 -> imageView.setImageResource(R.drawable.pepsi)
            2 -> imageView.setImageResource(R.drawable.milk)
            3 -> imageView.setImageResource(R.drawable.tea)
            4 -> imageView.setImageResource(R.drawable.beer)
            else -> {
                imageView.setImageResource(R.drawable.water)
            }
        }

        nameText.setText(drink.name)
        priceText.setText(drink.dPrice.toString())
    }

    //return the number of the items in the list
    override fun getItemCount(): Int {
        return dList.size
    }

    inner class ViewHolder(ItemView: View, onItemClickedFunction: (Int) -> Unit) : RecyclerView.ViewHolder(ItemView) {
        val productImageView: ImageView = ItemView.findViewById(R.id.img_product)
        val productNameView: TextView = ItemView.findViewById(R.id.product_name)
        val productPriceView: TextView = ItemView.findViewById(R.id.product_price)
        val addProductButton: Button = ItemView.findViewById(R.id.btn_plus)
        val delProductButton: Button = ItemView.findViewById(R.id.btn_sub)

        init {
            addProductButton.setOnClickListener {
                OrderActivity.checkStatusBtn = "ButtonPlus"
                onItemClickedFunction(adapterPosition)
            }

            delProductButton.setOnClickListener {
                OrderActivity.checkStatusBtn = "ButtonSub"
                onItemClickedFunction(adapterPosition)
            }
        }
    }
}