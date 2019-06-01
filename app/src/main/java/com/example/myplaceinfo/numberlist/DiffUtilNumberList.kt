package com.example.myplaceinfo.numberlist

import androidx.recyclerview.widget.DiffUtil
import com.example.myplaceinfo.data.NumbersEntity

/**
 * Created by Alexandr Mikhalev on 30.05.2019.
 *
 * @author Alexandr Mikhalev
 */
class DiffUtilNumberList(private val oldList: List<NumbersEntity>, private val newList: List<NumbersEntity>): DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        var isSame = true
        for (i in 0 until newList.size)
        {
            if (!((oldItem.numberStr == newItem.numberStr)&&(oldItem.text == newItem.text))) isSame = false
        }
        return isSame
    }
}