package com.example.myplaceinfo.years

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.myplaceinfo.R
import com.example.myplaceinfo.years.viewmodel.YearsViewModel
import com.example.myplaceinfo.years.viewmodel.YearsViewModelImpl

/**
 * Created by Alexandr Mikhalev on 28.05.2019.
 *
 * @author Alexandr Mikhalev
 */
class YearsFragment: Fragment() {
    private var binding: com.example.myplaceinfo.databinding.FragmentYearsBinding? = null
    private var yearsViewModel: YearsViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        yearsViewModel = ViewModelProviders.of(this).get(YearsViewModelImpl::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_years, container, false)
        binding!!.viewModel = yearsViewModel
        return binding!!.root

    }

    companion object {

        private val TAG = "MainActivity"

        fun newInstance(): YearsFragment {
            val args = Bundle()
            val fragment = YearsFragment()
            fragment.arguments = args
            return fragment
        }
    }
}