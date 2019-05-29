package com.example.myplaceinfo.date.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Alexandr Mikhalev on 28.05.2019.
 *
 * @author Alexandr Mikhalev
 */
interface DateAPI {
    @GET("{month}/{day}/date?json")
    fun messages(@Path("month")month: String, @Path("day")day: String): Call<DateIp>
}