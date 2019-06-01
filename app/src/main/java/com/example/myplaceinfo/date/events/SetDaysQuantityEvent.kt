package com.example.myplaceinfo.date.events

/**
 * Created by Alexandr Mikhalev on 28.05.2019.
 *
 * @author Alexandr Mikhalev
 */
class SetDaysQuantityEvent(val dayType: DayType) {

    enum class DayType {
        TWENTY_NINE,
        THIRTY,
        THIRTY_ONE
    }
}