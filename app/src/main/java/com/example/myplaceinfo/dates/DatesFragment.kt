package com.example.myplaceinfo.dates

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.myplaceinfo.R
import com.example.myplaceinfo.dates.events.CountDaysEvent
import com.example.myplaceinfo.dates.viewmodel.DatesViewModel
import com.example.myplaceinfo.dates.viewmodel.DatesViewModelImpl

/**
 * Created by Alexandr Mikhalev on 28.05.2019.
 *
 * @author Alexandr Mikhalev
 */
class DatesFragment : Fragment() {

    private var binding: com.example.myplaceinfo.databinding.FragmentDatesBinding? = null
    private var datesViewModel: DatesViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        datesViewModel = ViewModelProviders.of(this).get<DatesViewModelImpl>(DatesViewModelImpl::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dates, container, false)
        binding!!.viewModel = datesViewModel
        init()
        return binding!!.root
    }

    private fun init() {

        datesViewModel!!.daysSpinnerEvent
            .observe(this, Observer { setDaysSpinner(it.dayType) })

        binding!!.spinnerMonth.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                itemSelected: View,
                selectedItemPosition: Int,
                selectedId: Long
            ) {
                val monthArray = resources.getStringArray(R.array.month_list)
                datesViewModel!!.month.set(monthArray[selectedItemPosition])
                datesViewModel!!.onItemMonthSelectedCallback()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private fun setDaysSpinner(dayType: CountDaysEvent.DayType) {
        when (dayType) {
            CountDaysEvent.DayType.THIRTY_ONE -> initDaysSpinner(31)
            CountDaysEvent.DayType.THIRTY -> initDaysSpinner(30)
            CountDaysEvent.DayType.TWENTY_NINE -> initDaysSpinner(29)
        }
    }

    private fun initDaysSpinner(monthLength: Int) {
        val testArray = when (monthLength) {
            31 -> resources.getStringArray(R.array.month_list_long)
            30 -> resources.getStringArray(R.array.month_list_average)
            29 -> resources.getStringArray(R.array.month_list_short)
            else -> throw IllegalArgumentException()
        }

        val arrayAdapter = ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, testArray)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        binding!!.spinnerDay.adapter = arrayAdapter
    }

    companion object {

        private val TAG = "MainActivity"

        fun newInstance(): DatesFragment {
            val args = Bundle()
            val fragment = DatesFragment()
            fragment.arguments = args
            return fragment
        }
    }
}