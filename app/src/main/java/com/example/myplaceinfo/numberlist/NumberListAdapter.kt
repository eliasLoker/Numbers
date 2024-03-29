package com.example.myplaceinfo.numberlist

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myplaceinfo.R
import com.example.myplaceinfo.data.NumbersEntity
import com.example.myplaceinfo.databinding.NumberListItemBinding

/**
 * Created by Alexandr Mikhalev on 30.05.2019.
 *
 * @author Alexandr Mikhalev
 */
class NumberListAdapter : RecyclerView.Adapter<NumberListHolder>() {

    var data: List<NumbersEntity> = ArrayList()
    lateinit var listener: OnBasketClickListener

    fun setList(list: List<NumbersEntity>) {
        data = list
        Log.d("RV", data[0].text + " from setList")
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberListHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: NumberListItemBinding = DataBindingUtil.inflate(inflater, R.layout.number_list_item, parent, false)
        return NumberListHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: NumberListHolder, position: Int) {
        holder.bind(data[position])
        holder.numberListItemBinding.basketView.setOnClickListener {
            listener.onBasketClick(holder.numberListItemBinding.numberTextView.text.toString())
        }
    }

    fun setOnBasketClickListener(onBasketClickListener: OnBasketClickListener) {
        listener = onBasketClickListener
    }

}