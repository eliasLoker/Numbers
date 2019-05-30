package com.example.myplaceinfo.numberlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myplaceinfo.R
import com.example.myplaceinfo.app.App
import com.example.myplaceinfo.data.NumberDatabase
import com.example.myplaceinfo.data.NumberEntity
import com.example.myplaceinfo.numberlist.interactor.NumberListInteractor
import com.example.myplaceinfo.numberlist.viewmodel.NumberListFactory
import com.example.myplaceinfo.numberlist.viewmodel.NumberListViewModel
import com.example.myplaceinfo.numberlist.viewmodel.NumberListViewModelImpl

/**
 * Created by Alexandr Mikhalev on 29.05.2019.
 *
 * @author Alexandr Mikhalev
 */
class NumberListFragment : Fragment() {

    private var numberListViewModel: NumberListViewModel? = null
    private var fragmentNumbersListBinding: com.example.myplaceinfo.databinding.FragmentNumbersListBinding? = null

    private lateinit var recyclerView: RecyclerView
    private lateinit var numberListAdapter: NumberListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val numberDao = NumberDatabase.getNumberDatabase(activity!!.applicationContext)!!.numberDao()
        numberListViewModel = ViewModelProviders
            .of(this, NumberListFactory(numberListInteractor = NumberListInteractor(numberDao)))
            .get(NumberListViewModelImpl::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentNumbersListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_numbers_list, container, false)
        fragmentNumbersListBinding!!.viewModel = numberListViewModel
        init()
        //start
        recyclerView = fragmentNumbersListBinding!!.recyclerView
        val layoutManager: RecyclerView.LayoutManager =  LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager

        numberListAdapter = NumberListAdapter()
        recyclerView.adapter = numberListAdapter

        //numberListAdapterRefresh.data//addList
        //end
        return fragmentNumbersListBinding!!.root
    }

    private fun init() {
        numberListViewModel!!.updateListEvent.observe(this, Observer { setList(it.list) })
        Log.d("RV", "init")
    }

    private fun setList(numberList: List<NumberEntity>) {
        /*
        numberListAdapter.numberListAdapterRefresh(numberList)
        Log.d("RV", "setList")
        */
        val diffUtilNumberList = DiffUtilNumberList(numberListAdapter.data, numberList)
        val diffResult = DiffUtil.calculateDiff(diffUtilNumberList)
        numberListAdapter.numberListAdapterRefresh(numberList)
        diffResult.dispatchUpdatesTo(numberListAdapter)

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