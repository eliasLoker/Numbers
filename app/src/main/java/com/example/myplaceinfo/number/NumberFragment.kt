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
import com.example.myplaceinfo.number.retrofit.NumberMessage
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

    private var numberBinding: com.example.myplaceinfo.databinding.FragmentNumberBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        numberBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_number, container, false)
        numberBinding!!.viewModel = numberViewModel
        init()
        return numberBinding!!.root
    }

    private fun init() {
        numberViewModel.showNumberDialogEvent.observe(this, Observer { getMyIp(it.type, it.number) })
    }

    private fun getMyIp(type: String?, number: String?) {
        val messages = Controller.numberAPI.messages(type!!, number!!)

        messages.enqueue(object : Callback<NumberMessage> {
            override fun onFailure(call: Call<NumberMessage>, t: Throwable) {
                Toast.makeText(context, "ERROR", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<NumberMessage>, response: Response<NumberMessage>) {
                val message = response.body()!!.text
                numberViewModel.onResponseCallback(message)
                val showDetailsDialog = NumberDetailsDialog().newInstance(message)
                showDetailsDialog.show(childFragmentManager, "sdfsdfs")
            }
        })
    }

    override fun onClickCloseButton(isSaved: Boolean) {
        val type = numberBinding!!.spinner.selectedItem.toString()
        if (isSaved) numberViewModel.onClickDialogCloseButtonCallback(isSaved, type)
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