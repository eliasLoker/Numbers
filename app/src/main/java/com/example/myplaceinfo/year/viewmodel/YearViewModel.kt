package com.example.myplaceinfo.year.viewmodel

import androidx.databinding.ObservableField
import com.example.myplaceinfo.SingleLiveEvent
import com.example.myplaceinfo.year.events.ShowYearDialogEvent

/**
 * Created by Alexandr Mikhalev on 28.05.2019.
 *
 * @author Alexandr Mikhalev
 */
interface YearViewModel {

    val editText: ObservableField<String>

    val isSeekBarEnabled: ObservableField<Boolean>

    val textSeek: ObservableField<Int>

    val yearDialogEvent: SingleLiveEvent<ShowYearDialogEvent>

    fun onProgressChangedCallback(arg: Int)

    fun onCheckedChangedCallback(checked: Boolean)

    fun onClickShowButton()

    fun onResponseCallback(message: String?)

    fun onClickDialogCloseButtonListenerCallback(isSaved: Boolean)
}