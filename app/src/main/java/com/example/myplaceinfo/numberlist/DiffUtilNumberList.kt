package com.example.myplaceinfo.numberlist

import androidx.recyclerview.widget.DiffUtil
import com.example.myplaceinfo.data.NumberEntity

/**
 * Created by Alexandr Mikhalev on 30.05.2019.
 *
 * @author Alexandr Mikhalev
 */
class DiffUtilNumberList(private val oldList: List<NumberEntity>, private val newList: List<NumberEntity>): DiffUtil.Callback() {

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
            if (!((oldItem.name == newItem.name)&&(oldItem.text == newItem.text))) isSame = false
        }
        return isSame
    }
}