package com.example.myplaceinfo.date.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.myplaceinfo.SingleLiveEvent
import com.example.myplaceinfo.data.NumberEntity
import com.example.myplaceinfo.date.events.CountDaysEvent
import com.example.myplaceinfo.date.events.ShowDateDialogEvent
import com.example.myplaceinfo.date.interactor.DateInteractor

/**
 * Created by Alexandr Mikhalev on 28.05.2019.
 *
 * @author Alexandr Mikhalev
 */
class DateViewModelImpl(val dateInteractor: DateInteractor) : ViewModel(), DateViewModel {
    private val indexOfMonth: ObservableField<Int> = ObservableField(1)
    private val indexOfDay: ObservableField<Int> = ObservableField(1)

    private var message: String? = null

    override val showDateDialogEvent: SingleLiveEvent<ShowDateDialogEvent> = SingleLiveEvent()
    override val checkedChangedEventMonth: SingleLiveEvent<CountDaysEvent.DayType> = SingleLiveEvent()

    override fun onItemSelectedMonthCallback(index: Int) {
        indexOfMonth.set(index + 1)
        val dayType = when (index) {
            0, 2, 4, 6, 7, 9, 11 -> CountDaysEvent.DayType.THIRTY_ONE
            3, 5, 8, 10 -> CountDaysEvent.DayType.THIRTY
            1 -> CountDaysEvent.DayType.TWENTY_NINE
            else -> throw IllegalArgumentException()
        }
        if (index + 1 == indexOfMonth.get()) return
        checkedChangedEventMonth.postValue(dayType)
    }

    override fun onItemSelectedDayCallback(index: Int) {
        indexOfDay.set(index + 1)
    }

    override fun onClickShowButton() {
        showDateDialogEvent
            .postValue(value = ShowDateDialogEvent(indexOfMonth.get().toString(), indexOfDay.get().toString()))
    }

    override fun onResponseCallback(message: String?) {
        this.message = message
    }

    override fun onClickDialogCloseButtonListenerCallback(isSaved: Boolean) {
        if (!isSaved) return
        val numberEntity = NumberEntity("Date", "${indexOfMonth.get()}/${indexOfDay.get()}", message!!)
        dateInteractor.insertInDB(numberEntity).subscribe()
    }
}