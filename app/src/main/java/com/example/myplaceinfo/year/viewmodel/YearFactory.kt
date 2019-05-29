package com.example.myplaceinfo.year.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myplaceinfo.year.interactor.YearInteractor
import org.jetbrains.annotations.NotNull

/**
 * Created by Alexandr Mikhalev on 29.05.2019.
 *
 * @author Alexandr Mikhalev
 */
class YearFactory(val yearInteractor: YearInteractor): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(@NotNull modelClass:Class<T>): T {
        return YearViewModelImpl(yearInteractor) as T
    }
}