package com.example.myplaceinfo.number.retrofit

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
    @GET("{number}/{type}?json")
    fun messages(@Path("type")type: String, @Path("number")number: String): Call<MessageIp>
}