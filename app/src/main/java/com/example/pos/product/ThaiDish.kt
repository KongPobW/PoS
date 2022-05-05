package com.example.pos.product

class ThaiDish(Name: String, DefaultPrice: Int, ID: Int) {

    val id = ID
    val name = Name
    var quantity = 1
    val dPrice = DefaultPrice

    //Use for calculating subtotal of a product
    var sTotal = dPrice

    companion object {
        fun createThaiDishList(): ArrayList<ThaiDish> {
            val thaiDish = ArrayList<ThaiDish>()

            thaiDish.add(ThaiDish("Guay Teow", 40, 6001))
            thaiDish.add(ThaiDish("Laab", 45, 6002))
            thaiDish.add(ThaiDish("Som Tam", 50, 6003))
            thaiDish.add(ThaiDish("Tom Kha Gai", 65, 6004))
            thaiDish.add(ThaiDish("Tom Yum", 100, 6005))

            return thaiDish
        }
    }
}