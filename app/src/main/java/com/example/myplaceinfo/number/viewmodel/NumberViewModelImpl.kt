package com.example.myplaceinfo.number.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.myplaceinfo.SingleLiveEvent
import com.example.myplaceinfo.data.NumbersEntity
import com.example.myplaceinfo.number.events.ShowNumberDialogEvent
import com.example.myplaceinfo.number.interactor.NumberInteractor

/**
 * Created by Alexandr Mikhalev on 24.05.2019.
 *
 * @author Alexandr Mikhalev
 */
class NumberViewModelImpl(private val numberInteractor: NumberInteractor) : ViewModel(), NumberViewModel {

    override val number: ObservableField<String> = ObservableField("0")

    override val showNumberDialogEvent: SingleLiveEvent<ShowNumberDialogEvent> = SingleLiveEvent()

    private var typeNumber = "math"
    private var message: String? = ""

    override fun onClickShowFactButton() {
        val ipEvent = ShowNumberDialogEvent(typeNumber, number.get()!!)
        showNumberDialogEvent.postValue(ipEvent)
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

    override fun onItemSelectedSpinner(str: String, index: Int) {
        typeNumber = str.toLowerCase()
    }

    override fun onResponseCallback(message: String?) {
        this.message = message
    }

    override fun onClickDialogCloseButtonCallback(isSaved: Boolean, type: String) {
        if (!isSaved) return
        val numberEntity = NumbersEntity(type, number.get()!!, message!!)
        numberInteractor.writeToDataBase(numberEntity).subscribe()
    }
}