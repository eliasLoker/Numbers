package com.example.myplaceinfo.number.inject

import com.example.myplaceinfo.number.NumberFragment
import dagger.Subcomponent

/**
 * Created by Alexandr Mikhalev on 01.06.2019.
 *
 * @author Alexandr Mikhalev
 */
@NumberScope
@Subcomponent(modules = [NumberModule::class])
interface NumberComponent {

    fun inject(numberFragment: NumberFragment)
}