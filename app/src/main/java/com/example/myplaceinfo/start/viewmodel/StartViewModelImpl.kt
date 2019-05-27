package com.example.myplaceinfo.start.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.myplaceinfo.SingleLiveEvent
import com.example.myplaceinfo.start.events.FragmentEvent
import com.example.myplaceinfo.start.events.ShowIpEvent
import java.lang.NumberFormatException

/**
 * Created by Alexandr Mikhalev on 24.05.2019.
 *
 * @author Alexandr Mikhalev
 */
class StartViewModelImpl : ViewModel(), StartViewModel {

    override val number: ObservableField<String> = ObservableField("0")
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
        this.number.set(ip)
    }

    override fun onClickNumberButton(number: Int) {
        if (this.number.get()!!.length > 10) return
        if (this.number.get().equals("0")) {
            this.number.set(number.toString())
            return
        }
        try {
            this.number.get()!!.toInt()
        } catch (ex: NumberFormatException) {
            this.number.set(number.toString())
            return
        }
        this.number.set(this.number.get() + number.toString())
    }

    override fun onClickRandomButton(str: String) {
        this.number.set(str)
    }

    override fun onClickReset() {
        this.number.set("0")
    }

    override fun onClickNull() {
        if (number.get().equals("0")) return
        this.number.set(number.get() + "0")
    }
}