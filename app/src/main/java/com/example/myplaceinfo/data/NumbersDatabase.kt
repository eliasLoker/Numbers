package com.example.myplaceinfo.data

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Created by Alexandr Mikhalev on 29.05.2019.
 *
 * @author Alexandr Mikhalev
 */
@Database(entities = [(NumbersEntity::class)], version = 1)
abstract class NumbersDatabase : RoomDatabase() {
    abstract fun numberDao(): NumbersDao
    /*
    companion object {
        var INSTANCE: NumbersDatabase? = null
        fun getNumberDatabase(context: Context): NumbersDatabase? {
            if (INSTANCE == null) {
                synchronized(NumbersDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, NumbersDatabase::class.java, "myDB").build()
                }
            }
            return INSTANCE
        }
    }
    */
}