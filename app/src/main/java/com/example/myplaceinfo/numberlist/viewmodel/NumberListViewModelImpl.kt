package com.example.myplaceinfo.numberlist.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.myplaceinfo.SingleLiveEvent
import com.example.myplaceinfo.data.NumbersEntity
import com.example.myplaceinfo.numberlist.events.UpdateListEvent
import com.example.myplaceinfo.numberlist.interactor.NumberListInteractor
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Alexandr Mikhalev on 29.05.2019.
 *
 * @author Alexandr Mikhalev
 */
class NumberListViewModelImpl(private val numberListInteractor: NumberListInteractor) : ViewModel(), NumberListViewModel {

    override val stateEmptyTextView: ObservableField<Boolean> = ObservableField(false)
    override val stateRecycler: ObservableField<Boolean> = ObservableField(false)
    override val stateProgressBar: ObservableField<Boolean> = ObservableField(true)

    override val updateListEvent: SingleLiveEvent<UpdateListEvent> = SingleLiveEvent()

    private lateinit var list: MutableList<NumbersEntity>
    private val compositeDisposable = CompositeDisposable()

    init {
        val disposable = numberListInteractor.getAll()
            .subscribe { t: List<NumbersEntity>? ->
                list = t!!.toMutableList()
                if (list.isEmpty()) {
                    stateEmptyTextView.set(true)
                    stateProgressBar.set(false)
                } else {
                    updateListEvent.postValue(UpdateListEvent(list))
                    stateRecycler.set(true)
                    stateProgressBar.set(false)
                }

            }
        compositeDisposable.add(disposable)
    }

    override fun onBasketClickCallback(number: String) {
        val disposable = numberListInteractor.deleteByNumber(number)
            .subscribe {
                numberListInteractor.getAll()
                    .subscribe { t: List<NumbersEntity>? ->
                        list.clear()
                        list = t!!.toMutableList()
                        if (list.isEmpty()) {
                            stateEmptyTextView.set(true)
                            stateRecycler.set(false)
                        } else {
                            updateListEvent.postValue(UpdateListEvent(list))
                        }
                    }
            }
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        compositeDisposable.dispose()
    }
}