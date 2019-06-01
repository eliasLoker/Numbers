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

    fun onProgressChangedSeekBar(arg: Int)

    fun onCheckedChangedSwitch(checked: Boolean)

    fun onClickShowFactButton()

    fun onResponseCallback(message: String?)

    fun onClickDialogCloseButtonCallback(isSaved: Boolean)
}