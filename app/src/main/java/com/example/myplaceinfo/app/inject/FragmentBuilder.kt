package com.example.myplaceinfo.app.inject

import com.example.myplaceinfo.date.DateFragment
import com.example.myplaceinfo.date.inject.DateModule
import com.example.myplaceinfo.date.inject.DateScope
import com.example.myplaceinfo.number.NumberFragment
import com.example.myplaceinfo.number.inject.NumberModule
import com.example.myplaceinfo.number.inject.NumberScope
import com.example.myplaceinfo.year.YearFragment
import com.example.myplaceinfo.year.inject.YearModule
import com.example.myplaceinfo.year.inject.YearScope
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

    @ContributesAndroidInjector(modules = [DateModule::class])
    @DateScope
    fun contributeDateFragment(): DateFragment

    @ContributesAndroidInjector(modules = [YearModule::class])
    @YearScope
    fun contributeYearFragment(): YearFragment
}