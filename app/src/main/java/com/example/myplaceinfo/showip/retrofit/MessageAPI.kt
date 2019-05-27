package com.example.myplaceinfo.showip.retrofit

import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Alexandr Mikhalev on 27.05.2019.
 *
 * @author Alexandr Mikhalev
 */
interface MessageAPI {

    @GET("?format=json")
    fun messages(): Call<MessageIp>
}