package com.example.pos.product

class Drink(Name: String, DefaultPrice: Int, ID: Int) {

    val id = ID
    val name = Name
    var quantity = 1
    val dPrice = DefaultPrice

    //Use for calculating subtotal of a product
    var sTotal = dPrice

    companion object {
        fun createDrinkList(): ArrayList<Drink> {
            val drink = ArrayList<Drink>()

            drink.add(Drink("Water", 10, 2001))
            drink.add(Drink("Pepsi", 25, 2002))
            drink.add(Drink("Milk", 35, 2003))
            drink.add(Drink("Tea", 50, 2004))
            drink.add(Drink("Beer", 100, 2005))

            return drink
        }
    }
}