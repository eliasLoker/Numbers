package com.example.myplaceinfo.showip

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.myplaceinfo.Controller
import com.example.myplaceinfo.R
import com.example.myplaceinfo.showip.retrofit.MessageIp
import com.example.myplaceinfo.showip.viewmodel.ShowIpViewModel
import com.example.myplaceinfo.showip.viewmodel.ShowIpViewModelImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Alexandr Mikhalev on 24.05.2019.
 *
 * @author Alexandr Mikhalev
 */
class ShowIpFragment : Fragment() {

    private var mShowIpViewModel: ShowIpViewModel? = null
    private var mFragmentShowIpBinding: com.example.myplaceinfo.databinding.FragmentShowIpBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mShowIpViewModel = ViewModelProviders.of(this).get<ShowIpViewModelImpl>(ShowIpViewModelImpl::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mFragmentShowIpBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_show_ip, container, false)
        mFragmentShowIpBinding!!.viewModel = mShowIpViewModel
        init()
        return mFragmentShowIpBinding!!.root
    }

    private fun init() {
        mShowIpViewModel!!.showIpEvent.observe(this, Observer { getMyIp() })
    }

    private fun getMyIp() {
        val messages = Controller.messageIp.messages()

        messages.enqueue(object : Callback<MessageIp> {
            override fun onFailure(call: Call<MessageIp>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<MessageIp>, response: Response<MessageIp>) {
                mShowIpViewModel!!.onResponseCallback(response.body()!!.text)
            }
        })
    }

    companion object {

        private val TAG = "MainActivity"

        fun newInstance(): ShowIpFragment {
            val args = Bundle()
            val fragment = ShowIpFragment()
            fragment.arguments = args
            return fragment
        }
    }
}