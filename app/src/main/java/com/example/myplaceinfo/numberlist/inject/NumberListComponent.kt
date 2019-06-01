package com.example.myplaceinfo.numberlist.inject

import com.example.myplaceinfo.numberlist.NumberListFragment
import dagger.Subcomponent

/**
 * Created by Alexandr Mikhalev on 01.06.2019.
 *
 * @author Alexandr Mikhalev
 */
@NumberListScope
@Subcomponent(modules = [NumberListModule::class])
interface NumberListComponent {

    fun inject(numberListFragment: NumberListFragment)
}