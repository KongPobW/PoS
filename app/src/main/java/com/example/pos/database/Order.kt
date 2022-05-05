package com.example.pos.database

import androidx.room.*

//Room Database or Room Persistence Library
@Entity(tableName = "OrderTbl")
data class Order(
    @PrimaryKey(autoGenerate = true) val userID: Long?,
    @ColumnInfo(name = "branch_id") val branchID: Long?,
    @ColumnInfo(name = "staff_id") val staffID: Long?,
)