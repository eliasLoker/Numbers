package com.example.myplaceinfo.showip.retrofit

import com.google.gson.annotations.SerializedName

/**
 * Created by Alexandr Mikhalev on 27.05.2019.
 *
 * @author Alexandr Mikhalev
 */
class MessageIp(

    @SerializedName("ip")
    var ip: String? = null
)