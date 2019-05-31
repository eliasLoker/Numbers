package com.example.myplaceinfo.numberlist.viewmodel

import androidx.databinding.ObservableField
import com.example.myplaceinfo.SingleLiveEvent
import com.example.myplaceinfo.numberlist.events.UpdateListEvent

/**
 * Created by Alexandr Mikhalev on 29.05.2019.
 *
 * @author Alexandr Mikhalev
 */
interface NumberListViewModel {

    val stateProgressBar: ObservableField<Boolean>

    val stateRecycler: ObservableField<Boolean>

    val stateEmptyTextView: ObservableField<Boolean>

    val updateListEvent: SingleLiveEvent<UpdateListEvent>

    fun onBasketClickCallback(number: String)
}