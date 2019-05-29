package com.example.myplaceinfo.number.viewmodel

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.myplaceinfo.SingleLiveEvent
import com.example.myplaceinfo.data.NumberEntity
import com.example.myplaceinfo.number.events.ShowIpEvent
import com.example.myplaceinfo.number.interactor.NumberInteractor
import java.lang.IllegalArgumentException
import java.lang.NumberFormatException

/**
 * Created by Alexandr Mikhalev on 24.05.2019.
 *
 * @author Alexandr Mikhalev
 */
class NumberViewModelImpl(val numberInteractor: NumberInteractor) : ViewModel(), NumberViewModel {

    override val number: ObservableField<String> = ObservableField("0")
    override val progressBarState: ObservableField<Boolean> = ObservableField(false)

    private val typeNumber: ObservableField<String> = ObservableField("math")

    override val showIpEvent: SingleLiveEvent<ShowIpEvent> = SingleLiveEvent()

    private var message: String? = ""

    override fun onClickShowIpButton() {
        progressBarState.set(true)
        val ipEvent = ShowIpEvent(typeNumber.get().toString(), number.get().toString())
        showIpEvent.postValue(ipEvent)
    }

    override fun onClickShowDetailsButton() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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

    override fun onItemSelectedSpinnerCallback(index: Int) {
        val type = when(index) {
            0 -> "math"
            1 -> "trivia"
            else -> throw IllegalArgumentException()
        }
        typeNumber.set(type)
    }

    override fun onResponseCallback(message: String?) {
        this.message = message
        Log.d("NVM", this.message)
    }

    override fun onClickTestButton() {
        val numberEntity: NumberEntity = NumberEntity(number.get().toString(), message!!)
        numberInteractor.insertInDB(numberEntity).subscribe()
    }
}