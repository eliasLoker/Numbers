package com.example.myplaceinfo.year

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.myplaceinfo.*
import com.example.myplaceinfo.year.retrofit.YearsMessage
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
    private var yearBinding: com.example.myplaceinfo.databinding.FragmentYearBinding? = null

    @Inject
    lateinit var yearViewModel: YearViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        yearBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_year, container, false)
        yearBinding!!.viewModel = yearViewModel
        init()
        return yearBinding!!.root
    }

    private fun init() {
        yearViewModel.yearDialogEvent.observe(this, Observer { getYearInfo(it.year) })
    }

    private fun getYearInfo(year: String) {
        val messages = Controller.yearAPI.messages(year)

        messages.enqueue(object : Callback<YearsMessage> {
            override fun onFailure(call: Call<YearsMessage>, t: Throwable) {
                val errorDialog = ErrorConnectionDialog().newIntstance(t.message)
                errorDialog.show(childFragmentManager, "NumberDialog")
            }

            override fun onResponse(call: Call<YearsMessage>, response: Response<YearsMessage>) {
                if (response.isSuccessful) {
                    val message = response.body()!!.text
                    val showDetailsDialog = YearDetailsDialog().newInstance(message)
                    showDetailsDialog.show(childFragmentManager, "sdfsdfs")
                    yearViewModel.onResponseCallback(response.body()!!.text)
                } else {
                    val errorMessage = response.code()
                    val responseNotSuccessfulDialog = ResponseNotSuccessfulDialog().newIntstance(errorMessage.toString())
                    responseNotSuccessfulDialog.show(childFragmentManager, "KEY")
                }
            }
        })
    }

    override fun onClickCloseButton(isSaved: Boolean) {
        yearViewModel.onClickDialogCloseButtonCallback(isSaved)
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