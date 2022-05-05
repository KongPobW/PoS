package com.example.pos.database

import android.content.Context
import androidx.room.*

//Room Database or Room Persistence Library
@Database(entities = [Order::class, OrderLine::class], version = 1)
abstract class PoSAppDatabase : RoomDatabase() {

    abstract fun orderDao(): OrderDao
    abstract fun orderLineDao(): OrderLineDao

    companion object {

        private var instance: PoSAppDatabase? = null

        fun getInstance(context: Context): PoSAppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(context, PoSAppDatabase::class.java, "pos_app.db").build()
            }
            return instance as PoSAppDatabase
        }
    }
}