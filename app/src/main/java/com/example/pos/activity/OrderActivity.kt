package com.example.pos.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pos.*
import com.example.pos.adapter_list_order.*
import com.example.pos.adapter_product.*
import com.example.pos.database.Order
import com.example.pos.database.OrderLine
import com.example.pos.database.PoSAppDatabase
import com.example.pos.product.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class OrderActivity : AppCompatActivity() {

    private var totalPrice: Int = 0

    private var orderListMacaron = arrayListOf<Macaron>()
    private var orderListDrink = arrayListOf<Drink>()
    private var orderListDessert = arrayListOf<Dessert>()
    private var orderListFruit = arrayListOf<Fruit>()
    private var orderListIceCream = arrayListOf<IceCream>()
    private var orderListThaiDish = arrayListOf<ThaiDish>()

    companion object {
        var checkStatusBtn: String? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        val intent = intent
        val catID = intent.getIntExtra("CatID", 0)

        val totalPriceTV = findViewById(R.id.totalPriceCal) as TextView

        setStaffAndBranchIDText()

        when (catID) {

            MACARON_CATID -> {

                val rvMacaron = findViewById(R.id.rvListProduct) as RecyclerView
                rvMacaron.layoutManager = LinearLayoutManager(this)

                val macaron = Macaron.createMacaronList()

                val rvListOrder = findViewById(R.id.rvListOrder) as RecyclerView
                rvListOrder.layoutManager = LinearLayoutManager(this)

                val adapterListOrder = ListOrderMacaronAdapter(orderListMacaron)
                rvListOrder.adapter = adapterListOrder

                val adapterMacaron = MacaronAdapter(macaron) { macaronOrder ->

                    if (checkStatusBtn.equals("ButtonPlus")) {

                        if (orderListMacaron.contains(macaronOrder)) {
                            val indexOrder = orderListMacaron.indexOf(macaronOrder)
                            macaronOrder.quantity += 1
                            macaronOrder.sTotal += macaronOrder.dPrice
                            orderListMacaron.set(indexOrder, macaronOrder)
                            adapterListOrder.notifyItemChanged(indexOrder)
                        } else {
                            orderListMacaron.add(macaronOrder)
                            adapterListOrder.notifyItemChanged(orderListMacaron.size)
                        }

                    } else if (checkStatusBtn.equals("ButtonSub")) {

                        val indexOrder = orderListMacaron.indexOf(macaronOrder)

                        if (orderListMacaron.contains(macaronOrder) && macaronOrder.quantity > 1) {
                            macaronOrder.quantity = macaronOrder.quantity - 1
                            macaronOrder.sTotal -= macaronOrder.dPrice
                            orderListMacaron.set(indexOrder, macaronOrder)
                            adapterListOrder.notifyItemChanged(indexOrder)
                        } else if (orderListMacaron.contains(macaronOrder) && macaronOrder.quantity == 1) {
                            orderListMacaron.removeAt(indexOrder)
                            adapterListOrder.notifyItemRemoved(indexOrder)
                        } else {
                            Toast.makeText(this, "No Product in List", Toast.LENGTH_SHORT).show()
                        }
                    }

                    totalPrice = 0

                    for (item in orderListMacaron) {
                        totalPrice += item.sTotal
                    }

                    totalPriceTV.text = totalPrice.toString() + "฿"
                }

                rvMacaron.adapter = adapterMacaron
            }

            DRINK_CATID -> {

                val rvDrink = findViewById(R.id.rvListProduct) as RecyclerView
                rvDrink.layoutManager = LinearLayoutManager(this)

                val drink = Drink.createDrinkList()

                val rvListOrder = findViewById(R.id.rvListOrder) as RecyclerView
                rvListOrder.layoutManager = LinearLayoutManager(this)

                val adapterListOrder = ListOrderDrinkAdapter(orderListDrink)
                rvListOrder.adapter = adapterListOrder

                val adapterDrink = DrinkAdapter(drink) { drinkOrder ->

                    if (checkStatusBtn.equals("ButtonPlus")) {

                        if (orderListDrink.contains(drinkOrder)) {
                            val indexOrder = orderListDrink.indexOf(drinkOrder)
                            drinkOrder.quantity += 1
                            drinkOrder.sTotal += drinkOrder.dPrice
                            orderListDrink.set(indexOrder, drinkOrder)
                            adapterListOrder.notifyItemChanged(indexOrder)
                        } else {
                            orderListDrink.add(drinkOrder)
                            adapterListOrder.notifyItemChanged(orderListDrink.size)
                        }

                    } else if (checkStatusBtn.equals("ButtonSub")) {

                        val indexOrder = orderListDrink.indexOf(drinkOrder)

                        if (orderListDrink.contains(drinkOrder) && drinkOrder.quantity > 1) {
                            drinkOrder.quantity = drinkOrder.quantity - 1
                            drinkOrder.sTotal -= drinkOrder.dPrice
                            orderListDrink.set(indexOrder, drinkOrder)
                            adapterListOrder.notifyItemChanged(indexOrder)
                        } else if (orderListDrink.contains(drinkOrder) && drinkOrder.quantity == 1) {
                            orderListDrink.removeAt(indexOrder)
                            adapterListOrder.notifyItemRemoved(indexOrder)
                        } else {
                            Toast.makeText(this, "No Product in List", Toast.LENGTH_SHORT).show()
                        }
                    }

                    totalPrice = 0

                    for (item in orderListDrink) {
                        totalPrice += item.sTotal
                    }

                    totalPriceTV.text = totalPrice.toString() + "฿"
                }

                rvDrink.adapter = adapterDrink
            }

            DESSERT_CATID -> {

                val rvDessert = findViewById(R.id.rvListProduct) as RecyclerView
                rvDessert.layoutManager = LinearLayoutManager(this)

                val dessert = Dessert.createDessertList()

                val rvListOrder = findViewById(R.id.rvListOrder) as RecyclerView
                rvListOrder.layoutManager = LinearLayoutManager(this)

                val adapterListOrder = ListOrderDessertAdapter(orderListDessert)
                rvListOrder.adapter = adapterListOrder

                val adapterDessert = DessertAdapter(dessert) { dessertOrder ->

                    if (checkStatusBtn.equals("ButtonPlus")) {

                        if (orderListDessert.contains(dessertOrder)) {
                            val indexOrder = orderListDessert.indexOf(dessertOrder)
                            dessertOrder.quantity += 1
                            dessertOrder.sTotal += dessertOrder.dPrice
                            orderListDessert.set(indexOrder, dessertOrder)
                            adapterListOrder.notifyItemChanged(indexOrder)
                        } else {
                            orderListDessert.add(dessertOrder)
                            adapterListOrder.notifyItemChanged(orderListDessert.size)
                        }

                    } else if (checkStatusBtn.equals("ButtonSub")) {

                        val indexOrder = orderListDessert.indexOf(dessertOrder)

                        if (orderListDessert.contains(dessertOrder) && dessertOrder.quantity > 1) {
                            dessertOrder.quantity -= 1
                            dessertOrder.sTotal -= dessertOrder.dPrice
                            orderListDessert.set(indexOrder, dessertOrder)
                            adapterListOrder.notifyItemChanged(indexOrder)
                        } else if (orderListDessert.contains(dessertOrder) && dessertOrder.quantity == 1) {
                            orderListDessert.removeAt(indexOrder)
                            adapterListOrder.notifyItemRemoved(indexOrder)
                        } else {
                            Toast.makeText(this, "No Product in List", Toast.LENGTH_SHORT).show()
                        }
                    }

                    totalPrice = 0

                    for (item in orderListDessert) {
                        totalPrice += item.sTotal
                    }

                    totalPriceTV.text = totalPrice.toString() + "฿"
                }

                rvDessert.adapter = adapterDessert
            }

            FRUIT_CATID -> {

                val rvFruit = findViewById(R.id.rvListProduct) as RecyclerView
                rvFruit.layoutManager = LinearLayoutManager(this)

                val fruit = Fruit.createFruitList()

                val rvListOrder = findViewById(R.id.rvListOrder) as RecyclerView
                rvListOrder.layoutManager = LinearLayoutManager(this)

                val adapterListOrder = ListOrderFruitAdapter(orderListFruit)
                rvListOrder.adapter = adapterListOrder

                val adapterFruit = FruitAdapter(fruit) { fruitOrder ->

                    if (checkStatusBtn.equals("ButtonPlus")) {

                        if (orderListFruit.contains(fruitOrder)) {
                            val indexOrder = orderListFruit.indexOf(fruitOrder)
                            fruitOrder.quantity += 1
                            fruitOrder.sTotal += fruitOrder.dPrice
                            orderListFruit.set(indexOrder, fruitOrder)
                            adapterListOrder.notifyItemChanged(indexOrder)
                        } else {
                            orderListFruit.add(fruitOrder)
                            adapterListOrder.notifyItemChanged(orderListFruit.size)
                        }

                    } else if (checkStatusBtn.equals("ButtonSub")) {

                        val indexOrder = orderListFruit.indexOf(fruitOrder)

                        if (orderListFruit.contains(fruitOrder) && fruitOrder.quantity > 1) {
                            fruitOrder.quantity -= 1
                            fruitOrder.sTotal -= fruitOrder.dPrice
                            orderListFruit.set(indexOrder, fruitOrder)
                            adapterListOrder.notifyItemChanged(indexOrder)
                        } else if (orderListFruit.contains(fruitOrder) && fruitOrder.quantity == 1) {
                            orderListFruit.removeAt(indexOrder)
                            adapterListOrder.notifyItemRemoved(indexOrder)
                        } else {
                            Toast.makeText(this, "No Product in List", Toast.LENGTH_SHORT).show()
                        }
                    }

                    totalPrice = 0

                    for (item in orderListFruit) {
                        totalPrice += item.sTotal
                    }

                    totalPriceTV.text = totalPrice.toString() + "฿"
                }

                rvFruit.adapter = adapterFruit
            }

            ICECREAM_CATID -> {

                val rvIceCream = findViewById(R.id.rvListProduct) as RecyclerView
                rvIceCream.layoutManager = LinearLayoutManager(this)

                val iceCream = IceCream.createIceCreamList()

                val rvListOrder = findViewById(R.id.rvListOrder) as RecyclerView
                rvListOrder.layoutManager = LinearLayoutManager(this)

                val adapterListOrder = ListOrderIceCreamAdapter(orderListIceCream)
                rvListOrder.adapter = adapterListOrder

                val adapterIceCream = IceCreamAdapter(iceCream) { iceCreamOrder ->

                    if (checkStatusBtn.equals("ButtonPlus")) {

                        if (orderListIceCream.contains(iceCreamOrder)) {
                            val indexOrder = orderListIceCream.indexOf(iceCreamOrder)
                            iceCreamOrder.quantity += 1
                            iceCreamOrder.sTotal += iceCreamOrder.dPrice
                            orderListIceCream.set(indexOrder, iceCreamOrder)
                            adapterListOrder.notifyItemChanged(indexOrder)
                        } else {
                            orderListIceCream.add(iceCreamOrder)
                            adapterListOrder.notifyItemChanged(orderListIceCream.size)
                        }

                    } else if (checkStatusBtn.equals("ButtonSub")) {

                        val indexOrder = orderListIceCream.indexOf(iceCreamOrder)

                        if (orderListIceCream.contains(iceCreamOrder) && iceCreamOrder.quantity > 1) {
                            iceCreamOrder.quantity -= 1
                            iceCreamOrder.sTotal -= iceCreamOrder.dPrice
                            orderListIceCream.set(indexOrder, iceCreamOrder)
                            adapterListOrder.notifyItemChanged(indexOrder)
                        } else if (orderListIceCream.contains(iceCreamOrder) && iceCreamOrder.quantity == 1) {
                            orderListIceCream.removeAt(indexOrder)
                            adapterListOrder.notifyItemRemoved(indexOrder)
                        } else {
                            Toast.makeText(this, "No Product in List", Toast.LENGTH_SHORT).show()
                        }
                    }

                    totalPrice = 0

                    for (item in orderListIceCream) {
                        totalPrice += item.sTotal
                    }

                    totalPriceTV.text = totalPrice.toString() + "฿"
                }

                rvIceCream.adapter = adapterIceCream
            }

            THAIDISH_CATID -> {

                val rvthaiDish = findViewById(R.id.rvListProduct) as RecyclerView
                rvthaiDish.layoutManager = LinearLayoutManager(this)

                val thaiDish = ThaiDish.createThaiDishList()

                val rvListOrder = findViewById(R.id.rvListOrder) as RecyclerView
                rvListOrder.layoutManager = LinearLayoutManager(this)

                val adapterListOrder = ListOrderThaiDishAdapter(orderListThaiDish)
                rvListOrder.adapter = adapterListOrder

                val adapterThaiDish = ThaiDishAdapter(thaiDish) { thaiDishOrder ->

                    if (checkStatusBtn.equals("ButtonPlus")) {

                        if (orderListThaiDish.contains(thaiDishOrder)) {
                            val indexOrder = orderListThaiDish.indexOf(thaiDishOrder)
                            thaiDishOrder.quantity += 1
                            thaiDishOrder.sTotal += thaiDishOrder.dPrice
                            orderListThaiDish.set(indexOrder, thaiDishOrder)
                            adapterListOrder.notifyItemChanged(indexOrder)
                        } else {
                            orderListThaiDish.add(thaiDishOrder)
                            adapterListOrder.notifyItemChanged(orderListThaiDish.size)
                        }

                    } else if (checkStatusBtn.equals("ButtonSub")) {

                        val indexOrder = orderListThaiDish.indexOf(thaiDishOrder)

                        if (orderListThaiDish.contains(thaiDishOrder) && thaiDishOrder.quantity > 1) {
                            thaiDishOrder.quantity = thaiDishOrder.quantity - 1
                            thaiDishOrder.sTotal -= thaiDishOrder.dPrice
                            orderListThaiDish.set(indexOrder, thaiDishOrder)
                            adapterListOrder.notifyItemChanged(indexOrder)
                        } else if (orderListThaiDish.contains(thaiDishOrder) && thaiDishOrder.quantity == 1) {
                            orderListThaiDish.removeAt(indexOrder)
                            adapterListOrder.notifyItemRemoved(indexOrder)
                        } else {
                            Toast.makeText(this, "No Product in List", Toast.LENGTH_SHORT).show()
                        }
                    }

                    totalPrice = 0

                    for (item in orderListThaiDish) {
                        totalPrice += item.sTotal
                    }

                    totalPriceTV.text = totalPrice.toString() + "฿"
                }

                rvthaiDish.adapter = adapterThaiDish
            }
        }
    }

    fun onClickedBtnSubmit(view: View) {

        Log.i("OrderActivity", "Submit Button Clicked!!")

        GlobalScope.launch {

            val preferences = getSharedPreferences("setup", Context.MODE_PRIVATE)

            val branchID = preferences.getString("branchID", "")?.toLong()
            val staffID = preferences.getString("staffID", "")?.toLong()

            val intent = intent
            val catID = intent.getIntExtra("CatID", 0)

            if (catID == MACARON_CATID) {

                val order = Order(null, branchID, staffID)
                val db = PoSAppDatabase.getInstance(applicationContext)
                val userID: Long = db.orderDao().insert(order)

                for (item in orderListMacaron) {
                    val orderLine = OrderLine(
                        null,
                        userID,
                        item.id.toLong(),
                        item.name,
                        item.quantity,
                        item.sTotal
                    )
                    db.orderLineDao().insert(orderLine)
                }

            } else if (catID == DRINK_CATID) {

                val order = Order(null, branchID, staffID)
                val db = PoSAppDatabase.getInstance(applicationContext)
                val userID: Long = db.orderDao().insert(order)

                for (item in orderListDrink) {
                    val orderLine = OrderLine(
                        null,
                        userID,
                        item.id.toLong(),
                        item.name,
                        item.quantity,
                        item.sTotal
                    )
                    db.orderLineDao().insert(orderLine)
                }

            } else if (catID == DESSERT_CATID) {

                val order = Order(null, branchID, staffID)
                val db = PoSAppDatabase.getInstance(applicationContext)
                val userID: Long = db.orderDao().insert(order)

                for (item in orderListDessert) {
                    val orderLine = OrderLine(
                        null,
                        userID,
                        item.id.toLong(),
                        item.name,
                        item.quantity,
                        item.sTotal
                    )
                    db.orderLineDao().insert(orderLine)
                }

            } else if (catID == FRUIT_CATID) {

                val order = Order(null, branchID, staffID)
                val db = PoSAppDatabase.getInstance(applicationContext)
                val userID: Long = db.orderDao().insert(order)

                for (item in orderListFruit) {
                    val orderLine = OrderLine(
                        null,
                        userID,
                        item.id.toLong(),
                        item.name,
                        item.quantity,
                        item.sTotal
                    )
                    db.orderLineDao().insert(orderLine)
                }

            } else if (catID == THAIDISH_CATID) {

                val order = Order(null, branchID, staffID)
                val db = PoSAppDatabase.getInstance(applicationContext)
                val userID: Long = db.orderDao().insert(order)

                for (item in orderListThaiDish) {
                    val orderLine = OrderLine(
                        null,
                        userID,
                        item.id.toLong(),
                        item.name,
                        item.quantity,
                        item.sTotal
                    )
                    db.orderLineDao().insert(orderLine)
                }

            } else if (catID == ICECREAM_CATID) {

                val order = Order(null, branchID, staffID)
                val db = PoSAppDatabase.getInstance(applicationContext)
                val userID: Long = db.orderDao().insert(order)

                for (item in orderListIceCream) {
                    val orderLine = OrderLine(
                        null,
                        userID,
                        item.id.toLong(),
                        item.name,
                        item.quantity,
                        item.sTotal
                    )
                    db.orderLineDao().insert(orderLine)
                }
            }
        }

        Toast.makeText(this, "Submit Button Clicked", Toast.LENGTH_SHORT).show()
    }

    fun onClickedBtnRetrieve(view: View) {

        Log.i("OrderActivity", "Retrieve Button Clicked!!")

        GlobalScope.launch {

            val db = PoSAppDatabase.getInstance(applicationContext)
            val orders = db.orderDao().getAll()
            val orderLines = db.orderLineDao().getAll()

            Log.i("OrderActivity", "Orders:")
            for (order in orders) {
                Log.i(
                    "OrderActivity",
                    "User ID = ${order.userID}, Branch ID = ${order.branchID}, Staff ID = ${order.staffID}"
                )
            }

            Log.i("OrderActivity", "OrderLines:")
            for (orderLine in orderLines) {
                Log.i(
                    "OrderActivity",
                    "Order ID = ${orderLine.orderID}, User ID = ${orderLine.userID}, Product ID = ${orderLine.productID}, Product Name = ${orderLine.productName}, Quantity = ${orderLine.quantity}, Subtotal = ${orderLine.sTotal}"
                )
            }
        }

        Toast.makeText(this, "Retrieve Button Clicked", Toast.LENGTH_SHORT).show()
    }

    fun setStaffAndBranchIDText() {

        val txtBranchID = findViewById(R.id.branchID) as TextView
        val txtStaffID = findViewById(R.id.staffID) as TextView

        val preferences = getSharedPreferences("setup", Context.MODE_PRIVATE)

        txtBranchID.setText(preferences.getString("branchID", "BID"))
        txtStaffID.setText(preferences.getString("staffID", "SID"))

        if (txtBranchID.text.equals("BID")) {
            txtBranchID.text = "1111"
        }
        if (txtStaffID.text.equals("SID")) {
            txtStaffID.text = "1111"
        }

        val edit = preferences.edit()
        edit.putString("branchID", txtBranchID.text.toString())
        edit.putString("staffID", txtStaffID.text.toString())
        edit.commit()
    }
}