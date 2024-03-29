package com.example.myplaceinfo.date.inject

import androidx.lifecycle.ViewModelProviders
import com.example.myplaceinfo.data.NumbersDao
import com.example.myplaceinfo.date.DateFragment
import com.example.myplaceinfo.date.interactor.DateInteractor
import com.example.myplaceinfo.date.viewmodel.DateFactory
import com.example.myplaceinfo.date.viewmodel.DateViewModel
import com.example.myplaceinfo.date.viewmodel.DateViewModelImpl
import dagger.Module
import dagger.Provides

/**
 * Created by Alexandr Mikhalev on 01.06.2019.
 *
 * @author Alexandr Mikhalev
 */
@Module
class DateModule {

    @DateScope
    @Provides
    fun provideDateInteractor(numbersDao: NumbersDao) = DateInteractor(numbersDao)

    @DateScope
    @Provides
    fun provideDateFactory(dateInteractor: DateInteractor) = DateFactory(dateInteractor)

    @DateScope
    @Provides
    fun provideDateViewModel(dateFragment: DateFragment, dateFactory: DateFactory): DateViewModel {
        return ViewModelProviders.of(dateFragment, dateFactory).get(DateViewModelImpl::class.java)
    }

}