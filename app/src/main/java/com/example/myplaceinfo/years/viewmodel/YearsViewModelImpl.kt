package com.example.myplaceinfo.years.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.myplaceinfo.SingleLiveEvent
import com.example.myplaceinfo.years.events.ShowYearDialogEvent

/**
 * Created by Alexandr Mikhalev on 28.05.2019.
 *
 * @author Alexandr Mikhalev
 */
class YearsViewModelImpl: ViewModel(), YearsViewModel {

    override val isSeekBarEnabled: ObservableField<Boolean> = ObservableField(true)

    override val textSeek: ObservableField<String> = ObservableField("SIGN")

    override val yearDialogEvent: SingleLiveEvent<ShowYearDialogEvent> = SingleLiveEvent()

    override fun onProgressChangedCallback(arg: Int) {
        textSeek.set(arg.toString())
    }

    override fun onCheckedChangedCallback(checked: Boolean) {
        isSeekBarEnabled.set(checked)
    }

    override fun onClickShowButton() {
        yearDialogEvent.postValue(value = ShowYearDialogEvent(textSeek.get().toString()))
    }
}