package com.example.pos.database

import androidx.room.*

//Room Database or Room Persistence Library
@Entity(tableName = "OrderLineTbl")
data class OrderLine(
    @PrimaryKey(autoGenerate = true) val orderID: Long?,
    @ColumnInfo(name = "user_id") val userID: Long,
    @ColumnInfo(name = "product_id") val productID: Long,
    @ColumnInfo(name = "product_name") val productName: String,
    @ColumnInfo(name = "quantity") val quantity: Int,
    @ColumnInfo(name = "subtotal") val sTotal: Int,
)