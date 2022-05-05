package com.example.pos.product

class Dessert(Name: String, DefaultPrice: Int, ID: Int) {

    val id = ID
    val name = Name
    var quantity = 1
    val dPrice = DefaultPrice

    //Use for calculating subtotal of a product
    var sTotal = dPrice

    companion object {
        fun createDessertList(): ArrayList<Dessert> {
            val dessert = ArrayList<Dessert>()

            dessert.add(Dessert("Apple Pie", 60, 3001))
            dessert.add(Dessert("Baked Alaska", 90, 3002))
            dessert.add(Dessert("Banana Foster", 70, 3003))
            dessert.add(Dessert("Egg Tarts", 30, 3004))
            dessert.add(Dessert("Lemon Tarts", 20, 3005))

            return dessert
        }
    }
}