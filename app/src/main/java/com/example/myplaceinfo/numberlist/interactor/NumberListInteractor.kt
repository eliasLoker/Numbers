package com.example.myplaceinfo.numberlist.interactor

import com.example.myplaceinfo.data.NumberDao
import com.example.myplaceinfo.data.NumberEntity
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Alexandr Mikhalev on 29.05.2019.
 *
 * @author Alexandr Mikhalev
 */
class NumberListInteractor(val numberDao: NumberDao) {
    fun getAll(): Single<List<NumberEntity>> {
        return numberDao.getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun deleteByNumber(number: String): Completable {
        return numberDao.deleteByNumber(number)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}