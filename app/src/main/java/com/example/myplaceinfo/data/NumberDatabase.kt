package com.example.myplaceinfo.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Created by Alexandr Mikhalev on 29.05.2019.
 *
 * @author Alexandr Mikhalev
 */
@Database(entities = [(NumberEntity::class)], version = 1)
abstract class NumberDatabase : RoomDatabase() {
    abstract fun numberDao(): NumberDao

    companion object {
        var INSTANCE: NumberDatabase? = null
        fun getNumberDatabase(context: Context): NumberDatabase? {
            if (INSTANCE == null) {
                synchronized(NumberDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, NumberDatabase::class.java, "myDB").build()
                }
            }
            return INSTANCE
        }
    }
}