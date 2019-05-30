package com.example.myplaceinfo.year.interactor

import com.example.myplaceinfo.data.NumberDao
import com.example.myplaceinfo.data.NumberEntity
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Alexandr Mikhalev on 29.05.2019.
 *
 * @author Alexandr Mikhalev
 */
class YearInteractor(val numberDao: NumberDao) {

    fun insertInDB(numberEntity: NumberEntity): Completable {
        return numberDao.insert(numberEntity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}