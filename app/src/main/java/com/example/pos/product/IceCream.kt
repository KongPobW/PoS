package com.example.pos.product

class IceCream(Name: String, DefaultPrice: Int, ID: Int) {

    val id = ID
    val name = Name
    var quantity = 1
    val dPrice = DefaultPrice

    //Use for calculating subtotal of a product
    var sTotal = dPrice

    companion object {
        fun createIceCreamList(): ArrayList<IceCream> {
            val iceCream = ArrayList<IceCream>()

            iceCream.add(IceCream("Vanilla", 10, 5001))
            iceCream.add(IceCream("Strawberry", 10, 5002))
            iceCream.add(IceCream("Mint", 10, 5003))
            iceCream.add(IceCream("Lemon", 10, 5004))
            iceCream.add(IceCream("Chocolate", 10, 5005))

            return iceCream
        }
    }
}