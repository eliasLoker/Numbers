package com.example.myplaceinfo.date

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.myplaceinfo.Controller
import com.example.myplaceinfo.OnClickDialogCloseButtonListener
import com.example.myplaceinfo.R
import com.example.myplaceinfo.date.events.SetDaysQuantityEvent
import com.example.myplaceinfo.date.retrofit.DateIp
import com.example.myplaceinfo.date.viewmodel.DateViewModel
import dagger.android.support.AndroidSupportInjection
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Alexandr Mikhalev on 28.05.2019.
 *
 * @author Alexandr Mikhalev
 */
class DateFragment : Fragment(), OnClickDialogCloseButtonListener {

    private var binding: com.example.myplaceinfo.databinding.FragmentDatesBinding? = null

    @Inject
    lateinit var dateViewModel: DateViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
        /*
        val numbersDao = NumbersDatabase.getNumberDatabase(activity!!.applicationContext)!!.numbersDao()
        dateViewModel = ViewModelProviders
            .of(this, DateFactory(dateInteractor = DateInteractor(numbersDao)))
            .get(DateViewModelImpl::class.java)
        */
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dates, container, false)
        binding!!.viewModel = dateViewModel
        init()
        return binding!!.root
    }

    private fun init() {
        dateViewModel!!.showDateDialogEvent
            .observe(this, Observer { getDateInfo(it.month, it.day) })

        dateViewModel!!.checkedChangedQuantityEventMonth
            .observe(this, Observer { setDaysSpinner(it.dayType) })
    }

    private fun setDaysSpinner(dayType: SetDaysQuantityEvent.DayType) {
        val testArray = when (dayType) {
            SetDaysQuantityEvent.DayType.THIRTY_ONE -> resources.getStringArray(R.array.month_list_long)
            SetDaysQuantityEvent.DayType.THIRTY -> resources.getStringArray(R.array.month_list_average)
            SetDaysQuantityEvent.DayType.TWENTY_NINE -> resources.getStringArray(R.array.month_list_short)
        }

        val arrayAdapter = ArrayAdapter<String>(context!!, android.R.layout.simple_spinner_item, testArray)
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
                val showDetailsDialog = DateDetailsDialog().newInstance(response.body()!!.text)
                showDetailsDialog.show(childFragmentManager, "sdfsdfs")
                dateViewModel!!.onResponseCallback(response.body()!!.text)
            }
        })
    }

    override fun onClickCloseButton(isSaved: Boolean) {
        dateViewModel!!.onClickDialogCloseButtonCallback(isSaved)
    }

    companion object {

        fun newInstance(): DateFragment {
            val args = Bundle()
            val fragment = DateFragment()
            fragment.arguments = args
            return fragment
        }
    }
}