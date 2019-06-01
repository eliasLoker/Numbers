package com.example.myplaceinfo.date.inject

import com.example.myplaceinfo.date.DateFragment
import dagger.Subcomponent

/**
 * Created by Alexandr Mikhalev on 01.06.2019.
 *
 * @author Alexandr Mikhalev
 */
@DateScope
@Subcomponent(modules = [DateModule::class])
interface DateComponent {

    fun inject(dateFragment: DateFragment)
}