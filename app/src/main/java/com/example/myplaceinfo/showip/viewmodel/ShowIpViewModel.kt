package com.example.myplaceinfo.showip.viewmodel

import androidx.databinding.ObservableField
import com.example.myplaceinfo.SingleLiveEvent
import com.example.myplaceinfo.showip.events.FragmentEvent
import com.example.myplaceinfo.showip.events.ShowIpEvent

/**
 * Created by Alexandr Mikhalev on 24.05.2019.
 *
 * @author Alexandr Mikhalev
 */
interface ShowIpViewModel {

  val detailedState: ObservableField<Boolean>

  val ip: ObservableField<String>

  val progressBarState: ObservableField<Boolean>

  val showIpEvent: SingleLiveEvent<ShowIpEvent>

  val fragmentEvent: SingleLiveEvent<FragmentEvent>

  fun onClickShowIpButton()

  fun onClickShowDetailsButton()

  fun onResponseCallback(ip: String?)
}