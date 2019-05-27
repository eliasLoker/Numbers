package com.example.myplaceinfo

import com.example.myplaceinfo.showip.retrofit.MessageIp
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Alexandr Mikhalev on 27.05.2019.
 *
 * @author Alexandr Mikhalev
 */
object Controller {
    private val BASE_IP_URL = "https://api.ipify.org/"
    private val BASE_MESSAGE_DETAILED_URL = "https://ipapi.co/"

    val messageIp: MessageIp
        get() {
            val retrofit = getRetrofit(BASE_IP_URL)
            return retrofit.create(MessageIp::class.java)
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