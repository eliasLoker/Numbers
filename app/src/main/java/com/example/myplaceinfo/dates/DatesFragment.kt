package com.example.myplaceinfo.dates

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.myplaceinfo.R
import com.example.myplaceinfo.dates.viewmodel.DatesViewModel
import com.example.myplaceinfo.dates.viewmodel.DatesViewModelImpl

/**
 * Created by Alexandr Mikhalev on 28.05.2019.
 *
 * @author Alexandr Mikhalev
 */
class DatesFragment: Fragment() {

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
        binding!!.spinnerMonth.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent:AdapterView<*>, itemSelected:View, selectedItemPosition:Int, selectedId:Long) {
                val monthArray = resources.getStringArray(R.array.month_list)
                datesViewModel!!.month.set(monthArray[selectedItemPosition])
            }

            override fun onNothingSelected(parent:AdapterView<*>) {}
        }
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