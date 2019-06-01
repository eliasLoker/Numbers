package com.example.myplaceinfo.app.inject

import android.content.Context
import androidx.room.Room
import com.example.myplaceinfo.data.NumberDao
import com.example.myplaceinfo.data.NumberDatabase
import dagger.Module
import dagger.Provides

/**
 * Created by Alexandr Mikhalev on 01.06.2019.
 *
 * @author Alexandr Mikhalev
 */
@Module
class RoomModule {

    @AppScope
    @Provides
    fun provideNumberDatabase(context: Context): NumberDatabase {
        return Room.databaseBuilder(context, NumberDatabase::class.java, "myDB").build()
    }

    @AppScope
    @Provides
    fun provideNumberDao(numberDatabase: NumberDatabase): NumberDao {
        return numberDatabase.numberDao()
    }
}