package com.example.myplaceinfo.numberlist.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.myplaceinfo.data.NumberEntity
import com.example.myplaceinfo.number.interactor.NumberInteractor
import com.example.myplaceinfo.numberlist.interactor.NumberListInteractor

/**
 * Created by Alexandr Mikhalev on 29.05.2019.
 *
 * @author Alexandr Mikhalev
 */
class NumberListViewModelImpl(val numberInteractor: NumberListInteractor) : ViewModel(), NumberListViewModel {

    override val text: ObservableField<String> = ObservableField("HIIII")

    override fun onClickView() {
        val disposable = numberInteractor.getAll()
            .subscribe { t: NumberEntity? -> text.set(t!!.text) }
    }
}