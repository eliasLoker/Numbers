package com.example.myplaceinfo.dates.viewmodel

import androidx.databinding.ObservableField
import com.example.myplaceinfo.SingleLiveEvent
import com.example.myplaceinfo.dates.events.CountDaysEvent
import com.example.myplaceinfo.dates.events.ShowDateDialogEvent

/**
 * Created by Alexandr Mikhalev on 28.05.2019.
 *
 * @author Alexandr Mikhalev
 */
interface DatesViewModel {

    val month: ObservableField<String>

    val indexOfMonth: ObservableField<Int>

    val indexOfDay: ObservableField<Int>


    val checkedChangedEventMonth: SingleLiveEvent<CountDaysEvent.DayType>

    val showDateDialogEvent: SingleLiveEvent<ShowDateDialogEvent>

    fun onClickShowButton()

    fun onItemSelectedMonthCallback(index: Int)

    fun onItemSelectedDayCallback(index: Int)
}