package com.example.myplaceinfo.app.inject

import com.example.myplaceinfo.number.NumberFragment
import com.example.myplaceinfo.number.inject.NumberModule
import com.example.myplaceinfo.number.inject.NumberScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Alexandr Mikhalev on 01.06.2019.
 *
 * @author Alexandr Mikhalev
 */
@Module
interface FragmentBuilder {

    @ContributesAndroidInjector(modules = [NumberModule::class])
    @NumberScope
    fun contributeNumberFragment(): NumberFragment
}