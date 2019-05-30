package com.example.myplaceinfo.date.viewmodel

import com.example.myplaceinfo.SingleLiveEvent
import com.example.myplaceinfo.date.events.CountDaysEvent
import com.example.myplaceinfo.date.events.ShowDateDialogEvent

/**
 * Created by Alexandr Mikhalev on 28.05.2019.
 *
 * @author Alexandr Mikhalev
 */
interface DateViewModel {

    val checkedChangedEventMonth: SingleLiveEvent<CountDaysEvent.DayType>

    val showDateDialogEvent: SingleLiveEvent<ShowDateDialogEvent>

    fun onClickShowButton()

    fun onItemSelectedMonthCallback(index: Int)

    fun onItemSelectedDayCallback(index: Int)

    fun onResponseCallback(message: String?)

    fun onClickFavouritesButtonCallback()
}