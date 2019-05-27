package com.example.myplaceinfo.start.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Alexandr Mikhalev on 27.05.2019.
 *
 * @author Alexandr Mikhalev
 */
interface MessageAPI {

    //@GET("random/year?json")
    @GET("{number}/math?json")
    fun messages(@Path("number")number: String): Call<MessageIp>
}