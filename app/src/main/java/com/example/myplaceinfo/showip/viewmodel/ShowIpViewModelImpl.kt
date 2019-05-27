package com.example.myplaceinfo.showip.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.myplaceinfo.SingleLiveEvent
import com.example.myplaceinfo.showip.events.FragmentEvent
import com.example.myplaceinfo.showip.events.ShowIpEvent

/**
 * Created by Alexandr Mikhalev on 24.05.2019.
 *
 * @author Alexandr Mikhalev
 */
class ShowIpViewModelImpl : ViewModel(), ShowIpViewModel {

    override val detailedState: ObservableField<Boolean> = ObservableField(false)
    override val ip: ObservableField<String> = ObservableField(" ")
    override val progressBarState: ObservableField<Boolean> = ObservableField(false)
    override val showIpEvent: SingleLiveEvent<ShowIpEvent> = SingleLiveEvent()
    override val fragmentEvent: SingleLiveEvent<FragmentEvent> = SingleLiveEvent()

    override fun onClickShowIpButton() {
        //progressBarState.set((!progressBarState.get())!!)
        progressBarState.set(true)
        val ipEvent = ShowIpEvent()
        showIpEvent.postValue(ipEvent)
    }

    override fun onClickShowDetailsButton() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onResponseCallback(ip: String?) {
        progressBarState.set(false)
        this.ip.set(ip)
    }
}