package com.example.myplaceinfo.years.viewmodel

import androidx.databinding.ObservableField
import com.example.myplaceinfo.SingleLiveEvent
import com.example.myplaceinfo.years.events.ShowYearDialogEvent

/**
 * Created by Alexandr Mikhalev on 28.05.2019.
 *
 * @author Alexandr Mikhalev
 */
interface YearsViewModel {

    val isSeekBarEnabled: ObservableField<Boolean>

    val textSeek: ObservableField<String>

    val yearDialogEvent: SingleLiveEvent<ShowYearDialogEvent>

    fun onProgressChangedCallback(arg: Int)

    fun onCheckedChangedCallback(checked: Boolean)

    fun onClickShowButton()
}