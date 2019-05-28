package com.example.myplaceinfo.years.viewmodel

import androidx.databinding.ObservableField

/**
 * Created by Alexandr Mikhalev on 28.05.2019.
 *
 * @author Alexandr Mikhalev
 */
interface YearsViewModel {

    val isSeekBarEnabled: ObservableField<Boolean>

    val textSeek: ObservableField<String>

    fun onProgressChangedCallback(arg: Int)

    fun onCheckedChangedCallback(checked: Boolean)
}