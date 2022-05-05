package com.example.pos.database

import androidx.room.*

//Room Database or Room Persistence Library
@Dao
interface OrderLineDao {

    @Query("SELECT * FROM OrderLineTbl")
    fun getAll(): List<OrderLine>

    @Query("SELECT * FROM OrderLineTbl WHERE orderID LIKE :id LIMIT 1")
    fun findByID(id: Long): OrderLine

    @Insert
    fun insert(orderLine: OrderLine)

    @Delete
    fun delete(orderLine: OrderLine)

    @Query("DELETE FROM OrderLineTbl")
    fun deleteAll()
}