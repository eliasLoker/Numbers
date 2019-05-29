package com.example.myplaceinfo.numberlist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myplaceinfo.numberlist.interactor.NumberListInteractor
import org.jetbrains.annotations.NotNull

/**
 * Created by Alexandr Mikhalev on 29.05.2019.
 *
 * @author Alexandr Mikhalev
 */
class NumberListFactory(val numberListInteractor: NumberListInteractor) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(@NotNull modelClass:Class<T>): T {
        return NumberListViewModelImpl(numberListInteractor) as T
    }
}