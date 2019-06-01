package com.example.myplaceinfo.number.viewmodel

import androidx.databinding.ObservableField
import com.example.myplaceinfo.SingleLiveEvent
import com.example.myplaceinfo.number.events.ShowNumberDialogEvent

/**
 * Created by Alexandr Mikhalev on 24.05.2019.
 *
 * @author Alexandr Mikhalev
 */
interface NumberViewModel {

    val number: ObservableField<String>

    val showNumberDialogEvent: SingleLiveEvent<ShowNumberDialogEvent>

    fun onClickShowFactButton()

    fun onClickNumberButton(number: Int)

    fun onClickRandomButton(str: String)

    fun onClickReset()

    fun onClickNull()

    fun onItemSelectedSpinner(str: String, index: Int)

    fun onResponseCallback(message: String?)

    fun onClickDialogCloseButtonCallback(isSaved: Boolean, type: String)

}