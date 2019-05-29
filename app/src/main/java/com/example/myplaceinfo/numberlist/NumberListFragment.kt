package com.example.myplaceinfo.numberlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.myplaceinfo.R
import com.example.myplaceinfo.number.interactor.NumberInteractor
import com.example.myplaceinfo.numberlist.viewmodel.NumberListFactory
import com.example.myplaceinfo.numberlist.viewmodel.NumberListViewModel
import com.example.myplaceinfo.numberlist.viewmodel.NumberListViewModelImpl

/**
 * Created by Alexandr Mikhalev on 29.05.2019.
 *
 * @author Alexandr Mikhalev
 */
class NumberListFragment: Fragment() {

    private var numberListViewModel: NumberListViewModel? = null
    private var fragmentNumbersListBinding: com.example.myplaceinfo.databinding.FragmentNumbersListBinding? =  null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        numberListViewModel = ViewModelProviders
            .of(this, NumberListFactory(numberInteractor = NumberInteractor()))
            .get(NumberListViewModelImpl::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentNumbersListBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_numbers_list, container, false)
        fragmentNumbersListBinding!!.viewModel = numberListViewModel
        return fragmentNumbersListBinding!!.root
    }

    companion object {
        fun newInstance(): NumberListFragment {
            val args = Bundle()
            val fragment = NumberListFragment()
            fragment.arguments = args
            return fragment
        }
    }
}