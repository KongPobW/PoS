package com.example.pos.database

data class DataOrderLine(
    val id: Int,
    val oid: Int,
    val uid: Int,
    val pid: Int,
    val pname: String,
    val qty: Int,
    val stotal: Int,
)