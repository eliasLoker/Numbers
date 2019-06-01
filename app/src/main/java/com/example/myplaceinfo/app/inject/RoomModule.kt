package com.example.myplaceinfo.app.inject

import android.content.Context
import androidx.room.Room
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
    fun provideNumberDatabase(context: Context) =
        Room.databaseBuilder(context, NumberDatabase::class.java, "myDB").build()

    @AppScope
    @Provides
    fun provideNumberDao(numberDatabase: NumberDatabase) = numberDatabase.numberDao()
}