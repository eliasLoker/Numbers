package com.example.myplaceinfo.date.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myplaceinfo.date.interactor.DateInteractor
import org.jetbrains.annotations.NotNull

/**
 * Created by Alexandr Mikhalev on 29.05.2019.
 *
 * @author Alexandr Mikhalev
 */
class DateFactory(val dateInteractor: DateInteractor): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(@NotNull modelClass:Class<T>): T {
        return DateViewModelImpl(dateInteractor) as T
    }
}