package com.example.myplaceinfo.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Alexandr Mikhalev on 29.05.2019.
 *
 * @author Alexandr Mikhalev
 */
@Entity
class NumberEntity(
    @PrimaryKey
    val id: Long,
    val number: String,
    val text: String
) {

}