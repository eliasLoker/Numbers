package com.example.myplaceinfo.year.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Alexandr Mikhalev on 28.05.2019.
 *
 * @author Alexandr Mikhalev
 */
interface YearsAPI {
    @GET("{year}/year?json")
    fun messages(@Path("year")year: String): Call<YearsMessage>
}