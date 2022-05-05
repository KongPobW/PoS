package com.example.pos.product

class Fruit(Name: String, DefaultPrice: Int, ID: Int) {

    val id = ID
    val name = Name
    var quantity = 1
    val dPrice = DefaultPrice

    //Use for calculating subtotal of a product
    var sTotal = dPrice

    companion object {
        fun createFruitList(): ArrayList<Fruit> {
            val fruit = ArrayList<Fruit>()

            fruit.add(Fruit("Watermelon", 50, 4001))
            fruit.add(Fruit("Apple", 25, 4002))
            fruit.add(Fruit("Pineapple", 40, 4003))
            fruit.add(Fruit("Banana", 25, 4004))
            fruit.add(Fruit("Papaya", 55, 4005))

            return fruit
        }
    }
}