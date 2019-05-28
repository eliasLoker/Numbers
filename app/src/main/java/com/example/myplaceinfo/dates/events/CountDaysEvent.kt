package com.example.myplaceinfo.dates.events

/**
 * Created by Alexandr Mikhalev on 28.05.2019.
 *
 * @author Alexandr Mikhalev
 */
class CountDaysEvent(dayType: DayType) {
    val dayType: DayType = dayType

    enum class DayType {
            TWENTY_NINE,
            THIRTY,
            THIRTY_ONE
        }
}