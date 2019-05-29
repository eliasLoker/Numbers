package com.example.myplaceinfo.number.viewmodel

import androidx.databinding.ObservableField
import com.example.myplaceinfo.SingleLiveEvent
import com.example.myplaceinfo.number.events.ShowIpEvent

/**
 * Created by Alexandr Mikhalev on 24.05.2019.
 *
 * @author Alexandr Mikhalev
 */
interface NumberViewModel {

  val number: ObservableField<String>

  val progressBarState: ObservableField<Boolean>

  val showIpEvent: SingleLiveEvent<ShowIpEvent>

  fun onClickShowIpButton()

  fun onClickShowDetailsButton()

  fun onClickNumberButton(number: Int)

  fun onClickRandomButton(str: String)

  fun onClickReset()

  fun onClickNull()

  fun onItemSelectedSpinnerCallback(index: Int)

  fun onResponseCallback(message: String?)

  fun onClickTestButton()

}