package com.example.myplaceinfo.number.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myplaceinfo.number.interactor.NumberInteractor
import org.jetbrains.annotations.NotNull

/**
 * Created by Alexandr Mikhalev on 29.05.2019.
 *
 * @author Alexandr Mikhalev
 */
class NumberFactory(val numberInteractor: NumberInteractor) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(@NotNull modelClass: Class<T>): T {
        return NumberViewModelImpl(numberInteractor) as T
    }
}