package com.example.myplaceinfo.number

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
import com.example.myplaceinfo.number.retrofit.MessageIp
import com.example.myplaceinfo.number.viewmodel.NumberViewModel
import dagger.android.support.AndroidSupportInjection
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Alexandr Mikhalev on 24.05.2019.
 *
 * @author Alexandr Mikhalev
 */
class NumberFragment : Fragment(), OnClickDialogCloseButtonListener {

    @Inject
    lateinit var numberViewModel: NumberViewModel

    private var mFragmentNumberBinding: com.example.myplaceinfo.databinding.FragmentNumberBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mFragmentNumberBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_number, container, false)
        mFragmentNumberBinding!!.viewModel = numberViewModel
        init()
        return mFragmentNumberBinding!!.root
    }

    private fun init() {
        numberViewModel!!.showIpEvent.observe(this, Observer { getMyIp(it.type, it.number) })
    }

    private fun getMyIp(type: String?, number: String?) {
        val messages = Controller.messageAPI.messages(type!!, number!!)

        messages.enqueue(object : Callback<MessageIp> {
            override fun onFailure(call: Call<MessageIp>, t: Throwable) {
                Toast.makeText(context, "ERROR", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<MessageIp>, response: Response<MessageIp>) {
                numberViewModel!!.onResponseCallback(response.body()!!.text ?: "")
                val showDetailsDialog = NumberDetailsDialog().newInstance(response.body()!!.text)
                showDetailsDialog.show(childFragmentManager, "sdfsdfs")
            }
        })
    }

    override fun onClickCloseButton(isSaved: Boolean) {
        if (isSaved) numberViewModel!!.onClickDialogCloseButtonListenerCallback(
            isSaved,
            mFragmentNumberBinding!!.spinner.selectedItem.toString()
        )
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