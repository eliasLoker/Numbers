package com.example.myplaceinfo.year.inject

import androidx.lifecycle.ViewModelProviders
import com.example.myplaceinfo.data.NumberDao
import com.example.myplaceinfo.year.YearFragment
import com.example.myplaceinfo.year.interactor.YearInteractor
import com.example.myplaceinfo.year.viewmodel.YearFactory
import com.example.myplaceinfo.year.viewmodel.YearViewModel
import com.example.myplaceinfo.year.viewmodel.YearViewModelImpl
import dagger.Module
import dagger.Provides

/**
 * Created by Alexandr Mikhalev on 01.06.2019.
 *
 * @author Alexandr Mikhalev
 */
@Module
class YearModule {

    @YearScope
    @Provides
    fun provideYearInteractor(numberDao: NumberDao): YearInteractor {
        return YearInteractor(numberDao)
    }

    @YearScope
    @Provides
    fun provideYearFractory(yearInteractor: YearInteractor): YearFactory {
        return YearFactory(yearInteractor)
    }

    @YearScope
    @Provides
    fun provideYearViewModel(yearFragment: YearFragment, yearFactory: YearFactory): YearViewModel {
        return ViewModelProviders
            .of(yearFragment, yearFactory)
            .get(YearViewModelImpl::class.java)
    }
}