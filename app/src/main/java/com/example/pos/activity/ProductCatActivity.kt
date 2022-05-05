package com.example.pos.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.pos.*

class ProductCatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_cat)

        val intent = intent
        val btnInCommand = intent.getStringExtra("BtnInCommand")

        macaronButton(btnInCommand)

        drinkButton(btnInCommand)

        dessertButton(btnInCommand)

        fruitButton(btnInCommand)

        iceCreamButton(btnInCommand)

        thaiDishButton(btnInCommand)
    }

    private fun macaronButton(btnInCommand: String?) {

        val btnMacaron = findViewById(R.id.btn_macaron) as Button

        btnMacaron.setOnClickListener {

            if (btnInCommand.equals("New")) {

                val intent = Intent(this, OrderActivity::class.java).apply {
                }
                intent.putExtra("CatID", MACARON_CATID)
                startActivity(intent)

            } else {

                val intent = Intent(this, ViewDetailActivity::class.java).apply {
                }
                intent.putExtra("CatID", MACARON_CATID)
                startActivity(intent)
            }
        }
    }

    private fun drinkButton(btnInCommand: String?) {

        val btnDrink = findViewById(R.id.btn_drink) as Button

        btnDrink.setOnClickListener {

            if (btnInCommand.equals("New")) {

                val intent = Intent(this, OrderActivity::class.java).apply {
                }
                intent.putExtra("CatID", DRINK_CATID)
                startActivity(intent)

            } else {

                val intent = Intent(this, ViewDetailActivity::class.java).apply {
                }
                intent.putExtra("CatID", DRINK_CATID)
                startActivity(intent)
            }
        }
    }

    private fun dessertButton(btnInCommand: String?) {

        val btnDessert = findViewById(R.id.btn_dessert) as Button

        btnDessert.setOnClickListener {

            if (btnInCommand.equals("New")) {

                val intent = Intent(this, OrderActivity::class.java).apply {
                }
                intent.putExtra("CatID", DESSERT_CATID)
                startActivity(intent)

            } else {

                val intent = Intent(this, ViewDetailActivity::class.java).apply {
                }
                intent.putExtra("CatID", DESSERT_CATID)
                startActivity(intent)
            }
        }
    }

    private fun fruitButton(btnInCommand: String?) {

        val btnFruit = findViewById(R.id.btn_fruit) as Button

        btnFruit.setOnClickListener {

            if (btnInCommand.equals("New")) {

                val intent = Intent(this, OrderActivity::class.java).apply {
                }
                intent.putExtra("CatID", FRUIT_CATID)
                startActivity(intent)

            } else {

                val intent = Intent(this, ViewDetailActivity::class.java).apply {
                }
                intent.putExtra("CatID", FRUIT_CATID)
                startActivity(intent)
            }
        }
    }

    private fun iceCreamButton(btnInCommand: String?) {

        val btnIceCream = findViewById(R.id.btn_icecream) as Button

        btnIceCream.setOnClickListener {

            if (btnInCommand.equals("New")) {

                val intent = Intent(this, OrderActivity::class.java).apply {
                }
                intent.putExtra("CatID", ICECREAM_CATID)
                startActivity(intent)

            } else {

                val intent = Intent(this, ViewDetailActivity::class.java).apply {
                }
                intent.putExtra("CatID", ICECREAM_CATID)
                startActivity(intent)
            }
        }
    }

    private fun thaiDishButton(btnInCommand: String?) {

        val btnThaiDish = findViewById(R.id.btn_thaidish) as Button

        btnThaiDish.setOnClickListener {

            if (btnInCommand.equals("New")) {

                val intent = Intent(this, OrderActivity::class.java).apply {
                }
                intent.putExtra("CatID", THAIDISH_CATID)
                startActivity(intent)

            } else {

                val intent = Intent(this, ViewDetailActivity::class.java).apply {
                }
                intent.putExtra("CatID", THAIDISH_CATID)
                startActivity(intent)
            }
        }
    }
}