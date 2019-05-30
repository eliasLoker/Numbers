package com.example.myplaceinfo.numberlist

import androidx.recyclerview.widget.RecyclerView
import com.example.myplaceinfo.data.NumberEntity

/**
 * Created by Alexandr Mikhalev on 30.05.2019.
 *
 * @author Alexandr Mikhalev
 */
class NumberListHolder(numberListItemBinding: com.example.myplaceinfo.databinding.NumberListItemBinding) : RecyclerView.ViewHolder(numberListItemBinding.root) {
    var numberListItemBinding: com.example.myplaceinfo.databinding.NumberListItemBinding = numberListItemBinding

    fun bind(numberEntity: NumberEntity) {
            numberListItemBinding.number = numberEntity
            numberListItemBinding.executePendingBindings()
    }
}