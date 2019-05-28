package com.example.myplaceinfo.start

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
import com.example.myplaceinfo.ShowDetailsDialog
import com.example.myplaceinfo.start.retrofit.MessageIp
import com.example.myplaceinfo.start.viewmodel.StartViewModel
import com.example.myplaceinfo.start.viewmodel.StartViewModelImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Alexandr Mikhalev on 24.05.2019.
 *
 * @author Alexandr Mikhalev
 */
class StartFragment : Fragment() {

    private var mStartViewModel: StartViewModel? = null
    private var mFragmentShowIpBinding: com.example.myplaceinfo.databinding.FragmentShowIpBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mStartViewModel = ViewModelProviders.of(this).get<StartViewModelImpl>(StartViewModelImpl::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mFragmentShowIpBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_show_ip, container, false)
        mFragmentShowIpBinding!!.viewModel = mStartViewModel
        init()
        return mFragmentShowIpBinding!!.root
    }

    private fun init() {
        mStartViewModel!!.showIpEvent.observe(this, Observer { getMyIp(mStartViewModel!!.number.get()) })
    }

    private fun getMyIp(number: String?) {
        val messages = Controller.messageAPI.messages(number!!)

        messages.enqueue(object : Callback<MessageIp> {
            override fun onFailure(call: Call<MessageIp>, t: Throwable) {
                Toast.makeText(context, "ERROR", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<MessageIp>, response: Response<MessageIp>) {
                //mStartViewModel!!.onResponseCallback(response.body()!!.text)
                //Toast.makeText(context, response.body()!!.text, Toast.LENGTH_SHORT).show()
                val showDetailsDialog = ShowDetailsDialog().newInstance(response.body()!!.text)
                showDetailsDialog.show(childFragmentManager, "sdfsdfs")
            }
        })
    }

    companion object {

        private val TAG = "MainActivity"

        fun newInstance(): StartFragment {
            val args = Bundle()
            val fragment = StartFragment()
            fragment.arguments = args
            return fragment
        }
    }
}