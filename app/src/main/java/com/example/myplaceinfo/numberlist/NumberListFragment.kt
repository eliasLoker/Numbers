package com.example.myplaceinfo.numberlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myplaceinfo.R

/**
 * Created by Alexandr Mikhalev on 29.05.2019.
 *
 * @author Alexandr Mikhalev
 */
class NumberListFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view: View = inflater.inflate(R.layout.fragment_numbers_list, container, false)
        return view
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