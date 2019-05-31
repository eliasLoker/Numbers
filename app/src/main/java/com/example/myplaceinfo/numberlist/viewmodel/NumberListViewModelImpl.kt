package com.example.myplaceinfo.numberlist.viewmodel

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.myplaceinfo.SingleLiveEvent
import com.example.myplaceinfo.data.NumberEntity
import com.example.myplaceinfo.number.interactor.NumberInteractor
import com.example.myplaceinfo.numberlist.events.UpdateListEvent
import com.example.myplaceinfo.numberlist.interactor.NumberListInteractor

/**
 * Created by Alexandr Mikhalev on 29.05.2019.
 *
 * @author Alexandr Mikhalev
 */
class NumberListViewModelImpl(val numberInteractor: NumberListInteractor) : ViewModel(), NumberListViewModel {

    lateinit var list: List<NumberEntity>
        /*
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {}
    */

    override val updateListEvent: SingleLiveEvent<UpdateListEvent> = SingleLiveEvent()

    override val text: ObservableField<String> = ObservableField("HIIII")

    override val stateRecycler: ObservableField<Boolean> = ObservableField(false)

    init {
        val disposable = numberInteractor.getAll()
            .subscribe { t: List<NumberEntity>? ->
                list = t!!
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
}