package com.example.myplaceinfo.year

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.myplaceinfo.Controller
import com.example.myplaceinfo.OnClickDialogCloseButtonListener
import com.example.myplaceinfo.R
import com.example.myplaceinfo.year.retrofit.YearsIp
import com.example.myplaceinfo.year.viewmodel.YearViewModel
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
class YearFragment : Fragment(), OnClickDialogCloseButtonListener {
    private var binding: com.example.myplaceinfo.databinding.FragmentYearsBinding? = null

    @Inject
    lateinit var yearViewModel: YearViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
        /*
        val numbersDao = NumbersDatabase.getNumberDatabase(activity!!.applicationContext)!!.numbersDao()
        yearViewModel = ViewModelProviders
            .of(this, YearFactory(yearInteractor = YearInteractor(numbersDao)))
            .get(YearViewModelImpl::class.java)
        */
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
                yearViewModel!!.onResponseCallback(response.body()!!.text)
            }
        })
    }

    override fun onClickCloseButton(isSaved: Boolean) {
        yearViewModel!!.onClickDialogCloseButtonListenerCallback(isSaved)
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