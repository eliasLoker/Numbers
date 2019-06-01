package com.example.myplaceinfo.numberlist.interactor

import com.example.myplaceinfo.data.NumbersDao
import com.example.myplaceinfo.data.NumbersEntity
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Alexandr Mikhalev on 29.05.2019.
 *
 * @author Alexandr Mikhalev
 */
class NumberListInteractor(private val numbersDao: NumbersDao) {
    fun getAll(): Single<List<NumbersEntity>> {
        return numbersDao.getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun deleteByNumber(number: String): Completable {
        return numbersDao.deleteByNumber(number)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}