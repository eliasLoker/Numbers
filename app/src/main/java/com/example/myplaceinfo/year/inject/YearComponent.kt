package com.example.myplaceinfo.year.inject

import com.example.myplaceinfo.year.YearFragment
import dagger.Subcomponent

/**
 * Created by Alexandr Mikhalev on 01.06.2019.
 *
 * @author Alexandr Mikhalev
 */
@YearScope
@Subcomponent(modules = [YearModule::class])
interface YearComponent {

    fun inject(yearFragment: YearFragment)
}