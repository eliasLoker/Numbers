package com.example.myplaceinfo.numberlist.interactor

import com.example.myplaceinfo.data.NumberDao
import com.example.myplaceinfo.data.NumberEntity
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
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
}