package com.example.myplaceinfo.date.viewmodel

import androidx.lifecycle.ViewModel
import com.example.myplaceinfo.Constants
import com.example.myplaceinfo.SingleLiveEvent
import com.example.myplaceinfo.data.NumbersEntity
import com.example.myplaceinfo.date.events.SetDaysQuantityEvent
import com.example.myplaceinfo.date.events.ShowDateDialogEvent
import com.example.myplaceinfo.date.interactor.DateInteractor

/**
 * Created by Alexandr Mikhalev on 28.05.2019.
 *
 * @author Alexandr Mikhalev
 */
class DateViewModelImpl(private val dateInteractor: DateInteractor) : ViewModel(), DateViewModel {

    override val showDateDialogEvent: SingleLiveEvent<ShowDateDialogEvent> = SingleLiveEvent()
    override val checkedChangedQuantityEventMonth: SingleLiveEvent<SetDaysQuantityEvent> = SingleLiveEvent()

    private var indexOfMonth = 1
    private var indexOfDay = 1
    private var message: String? = null

    override fun onItemSelectedMonth(index: Int) {
        indexOfMonth = index + 1
        val dayType = when (index) {
            0, 2, 4, 6, 7, 9, 11 -> SetDaysQuantityEvent(SetDaysQuantityEvent.DayType.THIRTY_ONE)
            3, 5, 8, 10 -> SetDaysQuantityEvent(SetDaysQuantityEvent.DayType.THIRTY)
            1 -> SetDaysQuantityEvent(SetDaysQuantityEvent.DayType.TWENTY_NINE)
            else -> throw IllegalArgumentException()
        }
        checkedChangedQuantityEventMonth.postValue(dayType)
    }

    override fun onItemSelectedDay(index: Int) {
        indexOfDay = index + 1
    }

    override fun onClickShowFactButton() {
        showDateDialogEvent
            .postValue(ShowDateDialogEvent("$indexOfMonth", "$indexOfDay"))
    }

    override fun onResponseCallback(message: String?) {
        this.message = message
    }

    override fun onClickDialogCloseButtonCallback(isSaved: Boolean) {
        if (!isSaved) return
        val numberEntity = NumbersEntity(Constants.FOR_DATE_TYPE, "$indexOfMonth/$indexOfDay", message!!)
        dateInteractor.writeToDataBase(numberEntity).subscribe()
    }
}