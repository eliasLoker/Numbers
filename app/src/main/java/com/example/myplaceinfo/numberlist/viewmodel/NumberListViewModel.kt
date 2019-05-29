package com.example.myplaceinfo.numberlist.viewmodel

import androidx.databinding.ObservableField

/**
 * Created by Alexandr Mikhalev on 29.05.2019.
 *
 * @author Alexandr Mikhalev
 */
interface NumberListViewModel {

    val text: ObservableField<String>

    fun onClickView()
}