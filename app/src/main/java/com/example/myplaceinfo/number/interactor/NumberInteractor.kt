package com.example.myplaceinfo.number.interactor

import com.example.myplaceinfo.data.NumbersDao
import com.example.myplaceinfo.data.NumbersEntity
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Alexandr Mikhalev on 29.05.2019.
 *
 * @author Alexandr Mikhalev
 */
class NumberInteractor(val numbersDao: NumbersDao) {

    fun insertInDB(numbersEntity: NumbersEntity): Completable {
        return numbersDao.insert(numbersEntity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}