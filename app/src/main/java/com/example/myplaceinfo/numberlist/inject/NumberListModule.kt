package com.example.myplaceinfo.numberlist.inject

import androidx.lifecycle.ViewModelProviders
import com.example.myplaceinfo.data.NumberDao
import com.example.myplaceinfo.numberlist.NumberListFragment
import com.example.myplaceinfo.numberlist.interactor.NumberListInteractor
import com.example.myplaceinfo.numberlist.viewmodel.NumberListFactory
import com.example.myplaceinfo.numberlist.viewmodel.NumberListViewModel
import com.example.myplaceinfo.numberlist.viewmodel.NumberListViewModelImpl
import dagger.Module
import dagger.Provides

/**
 * Created by Alexandr Mikhalev on 01.06.2019.
 *
 * @author Alexandr Mikhalev
 */
@Module
class NumberListModule {

    @NumberListScope
    @Provides
    fun provideNumberListInteractor(numberDao: NumberDao) = NumberListInteractor(numberDao)

    @NumberListScope
    @Provides
    fun provideNumberListFactory(numberListInteractor: NumberListInteractor) = NumberListFactory(numberListInteractor)

    @NumberListScope
    @Provides
    fun provideNumberListViewModel(numberListFragment: NumberListFragment, numberListFactory: NumberListFactory)
            : NumberListViewModel {
        return ViewModelProviders.of(numberListFragment, numberListFactory).get(NumberListViewModelImpl::class.java)
    }

}