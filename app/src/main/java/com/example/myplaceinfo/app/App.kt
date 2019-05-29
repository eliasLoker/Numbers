package com.example.myplaceinfo.app

import android.app.Application
import androidx.room.Room
import com.example.myplaceinfo.data.NumberDatabase

/**
 * Created by Alexandr Mikhalev on 29.05.2019.
 *
 * @author Alexandr Mikhalev
 */
class App : Application()  {

    /*
    lateinit var database: NumberDatabase
    lateinit var instance: App

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(this, NumberDatabase::class.java, "database").build()
    }
    companion object {
        fun getInstance(): App {
            return
        }
    }
    */
}