package com.example.myplaceinfo.number.inject

import androidx.lifecycle.ViewModelProviders
import com.example.myplaceinfo.data.NumberDao
import com.example.myplaceinfo.number.NumberFragment
import com.example.myplaceinfo.number.interactor.NumberInteractor
import com.example.myplaceinfo.number.viewmodel.NumberFactory
import com.example.myplaceinfo.number.viewmodel.NumberViewModel
import com.example.myplaceinfo.number.viewmodel.NumberViewModelImpl
import dagger.Module
import dagger.Provides

/**
 * Created by Alexandr Mikhalev on 01.06.2019.
 *
 * @author Alexandr Mikhalev
 */
@Module
class NumberModule {

    @NumberScope
    @Provides
    fun provideNumberInteractor(numberDao: NumberDao) = NumberInteractor(numberDao)

    @NumberScope
    @Provides
    fun provideNumberFactory(numberInteractor: NumberInteractor) = NumberFactory(numberInteractor)

    @NumberScope
    @Provides
    fun provideNumberViewModel(numberFragment: NumberFragment, numberFactory: NumberFactory): NumberViewModel {
        return ViewModelProviders.of(numberFragment, numberFactory).get(NumberViewModelImpl::class.java)
    }

}