package com.example.pos.database

import androidx.room.*

//Room Database or Room Persistence Library
@Dao
interface OrderDao {

    @Query("SELECT * FROM OrderTbl")
    fun getAll(): List<Order>

    @Query("SELECT * FROM OrderTbl WHERE userID LIKE :id LIMIT 1")
    fun findByID(id: Long): Order

    @Insert
    fun insert(order: Order): Long

    @Delete
    fun delete(order: Order)

    @Query("DELETE FROM OrderTbl")
    fun deleteAll()
}