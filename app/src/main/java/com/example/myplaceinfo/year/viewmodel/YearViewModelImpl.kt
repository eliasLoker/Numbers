package com.example.myplaceinfo.year.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.myplaceinfo.SingleLiveEvent
import com.example.myplaceinfo.year.events.ShowYearDialogEvent
import java.lang.IllegalArgumentException

/**
 * Created by Alexandr Mikhalev on 28.05.2019.
 *
 * @author Alexandr Mikhalev
 */
class YearViewModelImpl: ViewModel(), YearViewModel {

    override val isSeekBarEnabled: ObservableField<Boolean> = ObservableField(true)

    override val editText: ObservableField<String> = ObservableField("")

    override val textSeek: ObservableField<String> = ObservableField("SIGN")

    override val yearDialogEvent: SingleLiveEvent<ShowYearDialogEvent> = SingleLiveEvent()

    override fun onProgressChangedCallback(arg: Int) {
        textSeek.set(arg.toString())
    }

    override fun onCheckedChangedCallback(checked: Boolean) {
        isSeekBarEnabled.set(checked)
    }

    override fun onClickShowButton() {
        val showYearDialogEvent = when (isSeekBarEnabled.get()) {
            true -> ShowYearDialogEvent(textSeek.get().toString())
            false -> ShowYearDialogEvent(editText.get().toString())
            null -> throw IllegalArgumentException()
        }
        yearDialogEvent.postValue(showYearDialogEvent)
    }
}