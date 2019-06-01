package com.example.myplaceinfo

import com.example.myplaceinfo.date.retrofit.DateAPI
import com.example.myplaceinfo.number.retrofit.MessageAPI
import com.example.myplaceinfo.year.retrofit.YearsAPI
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Alexandr Mikhalev on 27.05.2019.
 *
 * @author Alexandr Mikhalev
 */
object Controller {
    private const val BASE_IP_URL = "http://numbersapi.com/"

    val messageAPI: MessageAPI
        get() {
            val retrofit = getRetrofit(BASE_IP_URL)
            return retrofit.create(MessageAPI::class.java)
        }

    val dateAPI: DateAPI
        get() {
            val retrofit = getRetrofit(BASE_IP_URL)
            return retrofit.create(DateAPI::class.java)
        }

    val yearAPI: YearsAPI
        get() {
            val retrofit = getRetrofit(BASE_IP_URL)
            return retrofit.create(YearsAPI::class.java)
        }


    private fun getRetrofit(URL: String): Retrofit {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        return Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}