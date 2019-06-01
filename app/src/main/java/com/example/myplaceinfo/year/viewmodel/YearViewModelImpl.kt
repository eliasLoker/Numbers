package com.example.myplaceinfo.year.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.myplaceinfo.SingleLiveEvent
import com.example.myplaceinfo.data.NumbersEntity
import com.example.myplaceinfo.year.events.ShowYearDialogEvent
import com.example.myplaceinfo.year.interactor.YearInteractor

/**
 * Created by Alexandr Mikhalev on 28.05.2019.
 *
 * @author Alexandr Mikhalev
 */
class YearViewModelImpl(val yearInteractor: YearInteractor): ViewModel(), YearViewModel {

    override val isSeekBarEnabled: ObservableField<Boolean> = ObservableField(true)

    override val editText: ObservableField<String> = ObservableField("")

    override val textSeek: ObservableField<Int> = ObservableField(0)

    private var message: String? = null

    override val yearDialogEvent: SingleLiveEvent<ShowYearDialogEvent> = SingleLiveEvent()

    override fun onProgressChangedCallback(arg: Int) {
        textSeek.set(arg)
    }

    override fun onCheckedChangedCallback(checked: Boolean) {
        isSeekBarEnabled.set(checked)
    }

    override fun onClickShowButton() {
        val showYearDialogEvent = when (isSeekBarEnabled.get()!!) {
            true -> ShowYearDialogEvent(textSeek.get().toString())
            false -> ShowYearDialogEvent(editText.get()!!)
        }
        yearDialogEvent.postValue(showYearDialogEvent)
    }

    override fun onResponseCallback(message: String?) {
        this.message = message
    }

    override fun onClickDialogCloseButtonListenerCallback(isSaved: Boolean) {
        if (!isSaved) return
        val numberEntity = when(isSeekBarEnabled.get()!!) {
            true -> NumbersEntity("Year", textSeek.get().toString(), message!!)
            false -> NumbersEntity("Year", editText.get()!!, message!!)
        }
        yearInteractor.insertInDB(numberEntity).subscribe()
    }
}