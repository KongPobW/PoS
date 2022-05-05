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
import com.example.pos.product.ThaiDish

class ThaiDishAdapter(private val tList: List<ThaiDish>, private val onItemClicked: (ThaiDish) -> Unit) : RecyclerView.Adapter<ThaiDishAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val thaiDishView = inflater.inflate(R.layout.item_list_product, parent, false)

        return ViewHolder(thaiDishView) {
            onItemClicked(tList[it])
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val thaiDish: ThaiDish = tList[position]

        val imageView = holder.productImageView
        val nameText = holder.productNameView
        val priceText = holder.productPriceView

        when (position) {
            0 -> imageView.setImageResource(R.drawable.guayteow)
            1 -> imageView.setImageResource(R.drawable.laab)
            2 -> imageView.setImageResource(R.drawable.somtam)
            3 -> imageView.setImageResource(R.drawable.tomkhagai)
            4 -> imageView.setImageResource(R.drawable.tomyum)
            else -> {
                imageView.setImageResource(R.drawable.guayteow)
            }
        }

        nameText.setText(thaiDish.name)
        priceText.setText(thaiDish.dPrice.toString())
    }

    //return the number of the items in the list
    override fun getItemCount(): Int {
        return tList.size
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