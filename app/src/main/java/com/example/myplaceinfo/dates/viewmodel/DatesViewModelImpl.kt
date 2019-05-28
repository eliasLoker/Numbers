package com.example.myplaceinfo.dates.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.myplaceinfo.SingleLiveEvent
import com.example.myplaceinfo.dates.events.CheckedChangedEvent
import com.example.myplaceinfo.dates.events.CountDaysEvent
import com.example.myplaceinfo.dates.events.ShowDateDialogEvent
import java.lang.IllegalArgumentException

/**
 * Created by Alexandr Mikhalev on 28.05.2019.
 *
 * @author Alexandr Mikhalev
 */
class DatesViewModelImpl : ViewModel(), DatesViewModel {
    override val month: ObservableField<String> = ObservableField("january")
    override val indexOfMonth: ObservableField<Int> = ObservableField(1)
    override val indexOfDay: ObservableField<Int> = ObservableField(1)


    override val dayType: ObservableField<CountDaysEvent.DayType> = ObservableField(CountDaysEvent.DayType.THIRTY_ONE)

    override val daysSpinnerEvent: SingleLiveEvent<CountDaysEvent> = SingleLiveEvent()
    override val showDateDialogEvent: SingleLiveEvent<ShowDateDialogEvent> = SingleLiveEvent()
    override val checkedChangedEvent: SingleLiveEvent<CountDaysEvent.DayType> = SingleLiveEvent()

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
        /*
        when(month.get()) {
            "january", "march", "may", "july", "august", "october", "december" -> daysSpinnerEvent
                .postValue(CountDaysEvent(dayType = CountDaysEvent.DayType.THIRTY_ONE))
            "april","june", "september", "november" -> daysSpinnerEvent
                .postValue(CountDaysEvent(dayType = CountDaysEvent.DayType.THIRTY))
            "february" -> daysSpinnerEvent
                .postValue(CountDaysEvent(dayType = CountDaysEvent.DayType.TWENTY_NINE))
        }
        */
    }

    override fun onItemSelectedMonthCallback(index: Int) {
        month.set(index.toString())
        indexOfMonth.set(index + 1)

        val dayType = when (index) {
            0,2,4,6,7,9, 11 -> CountDaysEvent.DayType.THIRTY_ONE
            3,5,8,10 -> CountDaysEvent.DayType.THIRTY
            1 -> CountDaysEvent.DayType.TWENTY_NINE
            else -> throw IllegalArgumentException()
        }
        checkedChangedEvent.postValue(dayType)
    }

    override fun onItemSelectedDayCallback(index: Int) {
        indexOfDay.set(index)
    }

    override fun onClickShowButton() {
        showDateDialogEvent.postValue(value = ShowDateDialogEvent(indexOfMonth.get().toString(), indexOfDay.get().toString()))
    }
}