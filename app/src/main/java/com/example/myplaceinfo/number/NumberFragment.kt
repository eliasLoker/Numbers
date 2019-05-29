package com.example.myplaceinfo.number

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
import com.example.myplaceinfo.data.NumberDatabase
import com.example.myplaceinfo.number.interactor.NumberInteractor
import com.example.myplaceinfo.number.retrofit.MessageIp
import com.example.myplaceinfo.number.viewmodel.NumberFactory
import com.example.myplaceinfo.number.viewmodel.NumberViewModel
import com.example.myplaceinfo.number.viewmodel.NumberViewModelImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Alexandr Mikhalev on 24.05.2019.
 *
 * @author Alexandr Mikhalev
 */
class NumberFragment : Fragment(), NumberDetailsDialog.SaveToDBListener {

    private var mNumberViewModel: NumberViewModel? = null
    private var mFragmentShowIpBinding: com.example.myplaceinfo.databinding.FragmentNumberBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val numberDao = NumberDatabase.getNumberDatabase(activity!!.applicationContext)!!.numberDao()
        mNumberViewModel = ViewModelProviders
            .of(this, NumberFactory(numberInteractor = NumberInteractor(numberDao)))
            .get(NumberViewModelImpl::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mFragmentShowIpBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_number, container, false)
        mFragmentShowIpBinding!!.viewModel = mNumberViewModel
        init()
        return mFragmentShowIpBinding!!.root
    }

    private fun init() {
        mNumberViewModel!!.showIpEvent.observe(this, Observer { getMyIp(it.type, it.number) })
    }

    private fun getMyIp(type: String?, number: String?) {
        val messages = Controller.messageAPI.messages(type!!, number!!)

        messages.enqueue(object : Callback<MessageIp> {
            override fun onFailure(call: Call<MessageIp>, t: Throwable) {
                Toast.makeText(context, "ERROR", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<MessageIp>, response: Response<MessageIp>) {
                mNumberViewModel!!.onResponseCallback(response.body()!!.text)
                val showDetailsDialog = NumberDetailsDialog().newInstance(response.body()!!.text)
                showDetailsDialog.show(childFragmentManager, "sdfsdfs")
            }
        })
    }

    override fun save() {
        mNumberViewModel!!.onClickTestButton()
    }

    companion object {

        fun newInstance(): NumberFragment {
            val args = Bundle()
            val fragment = NumberFragment()
            fragment.arguments = args
            return fragment
        }
    }
}