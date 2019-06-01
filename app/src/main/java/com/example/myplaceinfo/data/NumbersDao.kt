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
interface NumbersDao {

    @Query("SELECT * FROM NumbersEntity")
    fun getAll(): Single<List<NumbersEntity>>

    @Insert
    fun insert(numbersEntity: NumbersEntity): Completable

    @Query("DELETE FROM NumbersEntity WHERE numberStr = :number")
    fun deleteByNumber(number: String): Completable
}