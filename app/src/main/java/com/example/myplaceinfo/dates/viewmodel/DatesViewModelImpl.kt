package com.example.myplaceinfo.dates.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

/**
 * Created by Alexandr Mikhalev on 28.05.2019.
 *
 * @author Alexandr Mikhalev
 */
class DatesViewModelImpl: ViewModel(), DatesViewModel {
    override val month: ObservableField<String> = ObservableField("january")
}