package com.example.myplaceinfo.numberlist.viewmodel

import androidx.databinding.ObservableField
import com.example.myplaceinfo.SingleLiveEvent
import com.example.myplaceinfo.data.NumberEntity
import com.example.myplaceinfo.numberlist.events.UpdateListEvent

/**
 * Created by Alexandr Mikhalev on 29.05.2019.
 *
 * @author Alexandr Mikhalev
 */
interface NumberListViewModel {

    val updateListEvent: SingleLiveEvent<UpdateListEvent>

    val stateRecycler: ObservableField<Boolean>

    val text: ObservableField<String>

    fun onClickView()

    fun getNumberList(): List<NumberEntity>

    fun onBasketClickCallback(number: String, positionInList: Int)
}