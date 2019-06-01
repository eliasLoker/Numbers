package com.example.myplaceinfo.app.inject

import android.content.Context
import androidx.room.Room
import com.example.myplaceinfo.data.NumbersDatabase
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
        Room.databaseBuilder(context, NumbersDatabase::class.java, "myDB").build()

    @AppScope
    @Provides
    fun provideNumberDao(numbersDatabase: NumbersDatabase) = numbersDatabase.numberDao()
}