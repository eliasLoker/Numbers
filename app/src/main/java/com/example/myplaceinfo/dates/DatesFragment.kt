package com.example.myplaceinfo.dates

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.myplaceinfo.Controller
import com.example.myplaceinfo.R
import com.example.myplaceinfo.dates.events.CountDaysEvent
import com.example.myplaceinfo.dates.retrofit.DateIp
import com.example.myplaceinfo.dates.viewmodel.DatesViewModel
import com.example.myplaceinfo.dates.viewmodel.DatesViewModelImpl
import com.example.myplaceinfo.dialogs.DatesDetailsDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        datesViewModel = ViewModelProviders.of(this).get(DatesViewModelImpl::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dates, container, false)
        binding!!.viewModel = datesViewModel
        init()
        return binding!!.root
    }

    private fun init() {
        datesViewModel!!.showDateDialogEvent
            .observe(this, Observer { getDateInfo(it.month, it.day) })

        datesViewModel!!.checkedChangedEventMonth
            .observe(this, Observer { setDaysSpinner(it) })
    }

    private fun setDaysSpinner(dayType: CountDaysEvent.DayType) {
        val testArray = when (dayType) {
            CountDaysEvent.DayType.THIRTY_ONE -> resources.getStringArray(R.array.month_list_long)
            CountDaysEvent.DayType.THIRTY -> resources.getStringArray(R.array.month_list_average)
            CountDaysEvent.DayType.TWENTY_NINE -> resources.getStringArray(R.array.month_list_short)
        }

        val arrayAdapter = ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, testArray)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        binding!!.spinnerDay.adapter = arrayAdapter
    }

    private fun getDateInfo(month: String, day: String) {
        val messages = Controller.dateAPI.messages(month, day)

        messages.enqueue(object : Callback<DateIp> {
            override fun onFailure(call: Call<DateIp>, t: Throwable) {
                Toast.makeText(context, "ERROR", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<DateIp>, response: Response<DateIp>) {
                val showDetailsDialog = DatesDetailsDialog().newInstance(response.body()!!.text)
                showDetailsDialog.show(childFragmentManager, "sdfsdfs")
            }
        })
    }

    companion object {

        fun newInstance(): DatesFragment {
            val args = Bundle()
            val fragment = DatesFragment()
            fragment.arguments = args
            return fragment
        }
    }
}