package com.example.pos.product

class Macaron(Name: String, DefaultPrice: Int, ID: Int) {

    val id = ID
    val name = Name
    var quantity = 1
    val dPrice = DefaultPrice

    //Use for calculating subtotal of a product
    var sTotal = dPrice

    companion object {
        fun createMacaronList(): ArrayList<Macaron> {
            val macaron = ArrayList<Macaron>()

            macaron.add(Macaron("Black Macaron", 45, 1001))
            macaron.add(Macaron("Blue Macaron", 55, 1002))
            macaron.add(Macaron("Green Macaron", 65, 1003))
            macaron.add(Macaron("Pink Macaron", 75, 1004))
            macaron.add(Macaron("Red Macaron", 85, 1005))

            return macaron
        }
    }
}