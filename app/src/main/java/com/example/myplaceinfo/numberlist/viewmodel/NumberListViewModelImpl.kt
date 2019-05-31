package com.example.myplaceinfo.numberlist.viewmodel

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.myplaceinfo.SingleLiveEvent
import com.example.myplaceinfo.data.NumberEntity
import com.example.myplaceinfo.numberlist.events.UpdateListEvent
import com.example.myplaceinfo.numberlist.interactor.NumberListInteractor

/**
 * Created by Alexandr Mikhalev on 29.05.2019.
 *
 * @author Alexandr Mikhalev
 */
class NumberListViewModelImpl(val numberListInteractor: NumberListInteractor) : ViewModel(), NumberListViewModel {

    lateinit var list: MutableList<NumberEntity>

    override val updateListEvent: SingleLiveEvent<UpdateListEvent> = SingleLiveEvent()

    override val text: ObservableField<String> = ObservableField("HIIII")

    override val stateRecycler: ObservableField<Boolean> = ObservableField(false)

    init {
        val disposable = numberListInteractor.getAll()
            .subscribe { t: List<NumberEntity>? ->
                list = t!!.toMutableList()
                updateListEvent.postValue(UpdateListEvent(list))
                stateRecycler.set(true)
                Log.d("RV", "stateRecycler.set(true)")
                Log.d("RV", list.size.toString())
            }
    }

    override fun onClickView() {

    }

    override fun getNumberList(): List<NumberEntity> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBasketClickCallback(number: String, positionInList: Int) {
        numberListInteractor.deleteByNumber(number)
            .subscribe {
                list.removeAt(positionInList)
                updateListEvent.postValue(UpdateListEvent(list))
            }
    }
}