package com.example.myplaceinfo.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by Alexandr Mikhalev on 29.05.2019.
 *
 * @author Alexandr Mikhalev
 */
@Dao
interface NumberDao {

    @Query("SELECT * FROM NumberEntity")
    fun getAll(): Single<NumberEntity>

    @Insert
    fun insert(numberEntity: NumberEntity): Completable
}