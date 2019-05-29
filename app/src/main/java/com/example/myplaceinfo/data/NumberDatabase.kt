package com.example.myplaceinfo.data

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Created by Alexandr Mikhalev on 29.05.2019.
 *
 * @author Alexandr Mikhalev
 */
@Database(entities = [(NumberEntity::class)], version = 1)
abstract class NumberDatabase : RoomDatabase() {
    abstract fun numberDao(): NumberDao
}