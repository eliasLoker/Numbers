package com.example.myplaceinfo.dates.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.myplaceinfo.SingleLiveEvent
import com.example.myplaceinfo.dates.events.CountDaysEvent
import com.example.myplaceinfo.dates.events.ShowDateDialogEvent

/**
 * Created by Alexandr Mikhalev on 28.05.2019.
 *
 * @author Alexandr Mikhalev
 */
class DatesViewModelImpl : ViewModel(), DatesViewModel {
    override val month: ObservableField<String> = ObservableField("january")
    override val daysSpinnerEvent: SingleLiveEvent<CountDaysEvent> = SingleLiveEvent()
    override val showDateDialogEvent: SingleLiveEvent<ShowDateDialogEvent> = SingleLiveEvent()

    override fun onItemMonthSelectedCallback() {
        /*
        when (month.get()) {
            "january" -> daysSpinnerEvent
                .postValue(CountDaysEvent(dayType = CountDaysEvent.DayType.THIRTY_ONE))
            "february" -> daysSpinnerEvent
                .postValue(CountDaysEvent(dayType = CountDaysEvent.DayType.TWENTY_NINE))
            "march" -> daysSpinnerEvent
                .postValue(CountDaysEvent(dayType = CountDaysEvent.DayType.THIRTY_ONE))
            "april" -> daysSpinnerEvent
                .postValue(CountDaysEvent(dayType = CountDaysEvent.DayType.THIRTY))
            "may" -> daysSpinnerEvent
                .postValue(CountDaysEvent(dayType = CountDaysEvent.DayType.THIRTY_ONE))
            "june" -> daysSpinnerEvent
                .postValue(CountDaysEvent(dayType = CountDaysEvent.DayType.THIRTY))
            "july" -> daysSpinnerEvent
                .postValue(CountDaysEvent(dayType = CountDaysEvent.DayType.THIRTY_ONE))
            "august" -> daysSpinnerEvent
                .postValue(CountDaysEvent(dayType = CountDaysEvent.DayType.THIRTY_ONE))
            "september" -> daysSpinnerEvent
                .postValue(CountDaysEvent(dayType = CountDaysEvent.DayType.THIRTY))
            "october" -> daysSpinnerEvent
                .postValue(CountDaysEvent(dayType = CountDaysEvent.DayType.THIRTY_ONE))
            "november" -> daysSpinnerEvent
                .postValue(CountDaysEvent(dayType = CountDaysEvent.DayType.THIRTY))
            "december" -> daysSpinnerEvent
                .postValue(CountDaysEvent(dayType = CountDaysEvent.DayType.THIRTY_ONE))
        }
        */
        when(month.get()) {
            "january", "march", "may", "july", "august", "october", "december" -> daysSpinnerEvent
                .postValue(CountDaysEvent(dayType = CountDaysEvent.DayType.THIRTY_ONE))
            "april","june", "september", "november" -> daysSpinnerEvent
                .postValue(CountDaysEvent(dayType = CountDaysEvent.DayType.THIRTY))
            "february" -> daysSpinnerEvent
                .postValue(CountDaysEvent(dayType = CountDaysEvent.DayType.TWENTY_NINE))
        }
    }

    override fun onClickShowButton() {
        showDateDialogEvent.postValue(value = ShowDateDialogEvent())
    }
}