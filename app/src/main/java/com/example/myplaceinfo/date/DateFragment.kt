package com.example.myplaceinfo.date

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.myplaceinfo.*
import com.example.myplaceinfo.date.events.SetDaysQuantityEvent
import com.example.myplaceinfo.date.retrofit.DateMessage
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

    @Inject
    lateinit var dateViewModel: DateViewModel

    private var binding: com.example.myplaceinfo.databinding.FragmentDateBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_date, container, false)
        binding!!.viewModel = dateViewModel
        init()
        return binding!!.root
    }

    private fun init() {
        dateViewModel.showDateDialogEvent
            .observe(this, Observer { getDateInfo(it.month, it.day) })

        dateViewModel.checkedChangedQuantityEventMonth
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

        messages.enqueue(object : Callback<DateMessage> {
            override fun onFailure(call: Call<DateMessage>, t: Throwable) {
                val errorDialog = ErrorConnectionDialog().newIntstance(t.message)
                errorDialog.show(childFragmentManager, "NumberDialog")
            }

            override fun onResponse(call: Call<DateMessage>, response: Response<DateMessage>) {
                if (response.isSuccessful) {
                    val message = response.body()!!.text
                    val showDetailsDialog = DateDetailsDialog().newInstance(message)
                    showDetailsDialog.show(childFragmentManager, "sdfsdfs")
                    dateViewModel.onResponseCallback(response.body()!!.text)
                } else {
                    val errorMessage = response.code()
                    val responseNotSuccessfulDialog = ResponseNotSuccessfulDialog().newIntstance(errorMessage.toString())
                    responseNotSuccessfulDialog.show(childFragmentManager, "KEY")
                }
            }
        })
    }

    override fun onClickCloseButton(isSaved: Boolean) {
        dateViewModel.onClickDialogCloseButtonCallback(isSaved)
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