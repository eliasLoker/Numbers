package com.example.myplaceinfo.years.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

/**
 * Created by Alexandr Mikhalev on 28.05.2019.
 *
 * @author Alexandr Mikhalev
 */
class YearsViewModelImpl: ViewModel(), YearsViewModel {
    override val isSeekBarEnabled: ObservableField<Boolean> = ObservableField(true)

    override val textSeek: ObservableField<String> = ObservableField("SIGN")

    override fun onProgressChangedCallback(arg: Int) {
        textSeek.set(arg.toString())
    }

    override fun onCheckedChangedCallback(checked: Boolean) {
        isSeekBarEnabled.set(checked)
    }
}