package com.example.myplaceinfo.numberlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myplaceinfo.R
import com.example.myplaceinfo.data.NumbersEntity
import com.example.myplaceinfo.numberlist.viewmodel.NumberListViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

/**
 * Created by Alexandr Mikhalev on 29.05.2019.
 *
 * @author Alexandr Mikhalev
 */
class NumberListFragment : Fragment(), OnBasketClickListener {

    @Inject
    lateinit var numberListViewModel: NumberListViewModel

    private var numberListBinding: com.example.myplaceinfo.databinding.FragmentNumberListBinding? = null

    private lateinit var recyclerView: RecyclerView
    private lateinit var numberListAdapter: NumberListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        numberListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_number_list, container, false)
        numberListBinding!!.viewModel = numberListViewModel
        init()
        //start
        recyclerView = numberListBinding!!.recyclerView
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager

        numberListAdapter = NumberListAdapter()
        recyclerView.adapter = numberListAdapter
        //end
        numberListAdapter.setOnBasketClickListener(this)
        return numberListBinding!!.root
    }

    private fun init() {
        numberListViewModel.updateListEvent.observe(this, Observer { setList(it.list) })
    }

    private fun setList(numbersList: List<NumbersEntity>) {
        val diffUtilNumberList = DiffUtilNumberList(numberListAdapter.data, numbersList)
        val diffResult = DiffUtil.calculateDiff(diffUtilNumberList)
        numberListAdapter.setList(numbersList)
        diffResult.dispatchUpdatesTo(numberListAdapter)

    }

    override fun onBasketClick(number: String) {
        numberListViewModel.onBasketClickCallback(number)
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