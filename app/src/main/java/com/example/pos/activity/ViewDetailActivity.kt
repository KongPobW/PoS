package com.example.pos.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.pos.*
import com.example.pos.databinding.ActivityViewDetailBinding
import com.example.pos.fragment.dessert.DessertFragment1
import com.example.pos.fragment.dessert.DessertFragment2
import com.example.pos.fragment.drink.DrinkFragment1
import com.example.pos.fragment.drink.DrinkFragment2
import com.example.pos.fragment.fruit.FruitFragment1
import com.example.pos.fragment.fruit.FruitFragment2
import com.example.pos.fragment.icecream.IceCreamFragment1
import com.example.pos.fragment.icecream.IceCreamFragment2
import com.example.pos.fragment.macaron.MacaronFragment1
import com.example.pos.fragment.macaron.MacaronFragment2
import com.example.pos.fragment.thaidish.ThaiDishFragment1
import com.example.pos.fragment.thaidish.ThaiDishFragment2

class ViewDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        val catID = intent.getIntExtra("CatID", 0)

        supportFragmentManager.commit {
            setReorderingAllowed(true)

            if (catID == MACARON_CATID) {
                add<MacaronFragment1>(R.id.list_product_fragmentContainerView)
            } else if (catID == DRINK_CATID) {
                add<DrinkFragment1>(R.id.list_product_fragmentContainerView)
            } else if (catID == DESSERT_CATID) {
                add<DessertFragment1>(R.id.list_product_fragmentContainerView)
            } else if (catID == FRUIT_CATID) {
                add<FruitFragment1>(R.id.list_product_fragmentContainerView)
            } else if (catID == THAIDISH_CATID) {
                add<ThaiDishFragment1>(R.id.list_product_fragmentContainerView)
            } else if (catID == ICECREAM_CATID) {
                add<IceCreamFragment1>(R.id.list_product_fragmentContainerView)
            }
        }

        val view: FragmentContainerView? = binding.root.findViewById(R.id.detail_product_fragmentContainerView)

        if (view != null) {

            supportFragmentManager.commit {

                setReorderingAllowed(true)

                if (catID == MACARON_CATID) {
                    add<MacaronFragment2>(R.id.detail_product_fragmentContainerView)
                } else if (catID == DRINK_CATID) {
                    add<DrinkFragment2>(R.id.detail_product_fragmentContainerView)
                } else if (catID == DESSERT_CATID) {
                    add<DessertFragment2>(R.id.detail_product_fragmentContainerView)
                } else if (catID == FRUIT_CATID) {
                    add<FruitFragment2>(R.id.detail_product_fragmentContainerView)
                } else if (catID == THAIDISH_CATID) {
                    add<ThaiDishFragment2>(R.id.detail_product_fragmentContainerView)
                } else if (catID == ICECREAM_CATID) {
                    add<IceCreamFragment2>(R.id.detail_product_fragmentContainerView)
                }
            }
        }
    }
}