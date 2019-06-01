package com.example.myplaceinfo.date.viewmodel

import com.example.myplaceinfo.SingleLiveEvent
import com.example.myplaceinfo.date.events.SetDaysQuantityEvent
import com.example.myplaceinfo.date.events.ShowDateDialogEvent

/**
 * Created by Alexandr Mikhalev on 28.05.2019.
 *
 * @author Alexandr Mikhalev
 */
interface DateViewModel {

    val checkedChangedQuantityEventMonth: SingleLiveEvent<SetDaysQuantityEvent>

    val showDateDialogEvent: SingleLiveEvent<ShowDateDialogEvent>

    fun onItemSelectedMonth(index: Int)

    fun onItemSelectedDay(index: Int)

    fun onClickShowFactButton()

    fun onResponseCallback(message: String?)

    fun onClickDialogCloseButtonCallback(isSaved: Boolean)
}