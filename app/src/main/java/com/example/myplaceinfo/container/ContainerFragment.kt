package com.example.my

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.myplaceinfo.R
import com.example.myplaceinfo.container.ViewPagerAdapter
import com.example.myplaceinfo.start.StartFragment
import com.google.android.material.tabs.TabLayout


/**
 * Created by Alexandr Mikhalev on 27.05.2019.
 *
 * @author Alexandr Mikhalev
 */
class ContainerFragment : Fragment() {

    private lateinit var toolbar: Toolbar
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_container, container, false)

        val actionBar = activity?.actionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        viewPager = view.findViewById(R.id.viewpager)
        setupViewPager(viewPager)

        tabLayout = view.findViewById(R.id.tablayout)
        tabLayout.setupWithViewPager(viewPager)
        return view
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(activity!!.supportFragmentManager)
        adapter.addFragment(StartFragment.newInstance(), "Numbers")
        adapter.addFragment(StartFragment.newInstance(), "Dates")
        viewPager.adapter = adapter
    }

    companion object {

        private val TAG = "MainActivity"

        fun newInstance(): ContainerFragment {
            val args = Bundle()
            val fragment = ContainerFragment()
            fragment.arguments = args
            return fragment
        }
    }
}