package com.example.myplaceinfo.year

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.myplaceinfo.Controller
import com.example.myplaceinfo.R
import com.example.myplaceinfo.dialogs.YearDetailsDialog
import com.example.myplaceinfo.year.interactor.YearInteractor
import com.example.myplaceinfo.year.retrofit.YearsIp
import com.example.myplaceinfo.year.viewmodel.YearFactory
import com.example.myplaceinfo.year.viewmodel.YearViewModel
import com.example.myplaceinfo.year.viewmodel.YearViewModelImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Alexandr Mikhalev on 28.05.2019.
 *
 * @author Alexandr Mikhalev
 */
class YearFragment : Fragment() {
    private var binding: com.example.myplaceinfo.databinding.FragmentYearsBinding? = null
    private var yearViewModel: YearViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        yearViewModel = ViewModelProviders
            .of(this, YearFactory(yearInteractor = YearInteractor()))
            .get(YearViewModelImpl::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_years, container, false)
        binding!!.viewModel = yearViewModel
        init()
        return binding!!.root
    }

    private fun init() {
        yearViewModel!!.yearDialogEvent.observe(this, Observer { getYearInfo(it.year) })
    }

    private fun getYearInfo(year: String) {
        val messages = Controller.yearAPI.messages(year)

        messages.enqueue(object : Callback<YearsIp> {
            override fun onFailure(call: Call<YearsIp>, t: Throwable) {
                Toast.makeText(context, "ERROR", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<YearsIp>, response: Response<YearsIp>) {
                val showDetailsDialog = YearDetailsDialog().newInstance(response.body()!!.text)
                showDetailsDialog.show(childFragmentManager, "sdfsdfs")
            }
        })
    }

    companion object {

        fun newInstance(): YearFragment {
            val args = Bundle()
            val fragment = YearFragment()
            fragment.arguments = args
            return fragment
        }
    }
}