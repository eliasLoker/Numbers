package com.example.myplaceinfo.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Alexandr Mikhalev on 29.05.2019.
 *
 * @author Alexandr Mikhalev
 */
@Entity
class NumberEntity(var name: String, var text: String) {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
}
