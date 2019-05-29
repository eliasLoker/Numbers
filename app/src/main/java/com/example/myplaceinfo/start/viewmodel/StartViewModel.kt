package com.example.myplaceinfo.start.viewmodel

import androidx.databinding.ObservableField
import com.example.myplaceinfo.SingleLiveEvent
import com.example.myplaceinfo.start.events.FragmentEvent
import com.example.myplaceinfo.start.events.ShowIpEvent

/**
 * Created by Alexandr Mikhalev on 24.05.2019.
 *
 * @author Alexandr Mikhalev
 */
interface StartViewModel {

  val number: ObservableField<String>

  val progressBarState: ObservableField<Boolean>

  val showIpEvent: SingleLiveEvent<ShowIpEvent>

  fun onClickShowIpButton()

  fun onClickShowDetailsButton()

  fun onResponseCallback(ip: String?)

  fun onClickNumberButton(number: Int)

  fun onClickRandomButton(str: String)

  fun onClickReset()

  fun onClickNull()

  fun onItemSelectedSpinnerCallback(index: Int)
}