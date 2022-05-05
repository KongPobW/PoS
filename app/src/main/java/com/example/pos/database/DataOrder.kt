package com.example.pos.database

data class DataOrder(
    val uid: Int,
    val bid: Int,
    val sid: Int,
    val createAt: String,
    val updateAt: String,
)