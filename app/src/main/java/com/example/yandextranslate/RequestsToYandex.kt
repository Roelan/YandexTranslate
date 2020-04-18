package com.example.yandextranslate

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.HttpUrl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class RequestsToYandex {

    private val gson: Gson = GsonBuilder().create()

    val yandexUrl = "http://127.0.0.1:8080"

    val KEY = "trnsl.1.1.20200413T221437Z.155bf65b20a757c5.5ebf2c44c876f664e5f2e75f5fe5d4d72ca90f83"

    fun myRequest(text: String): String {
        var translateText: String = "Lets go"
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(yandexUrl)
            .build()

        val api: Link = retrofit.create(Link::class.java)
        val myData = TranslationData(KEY, text, "en-ru")

        val call = api.translate(myData)

        call.enqueue(object : Callback<TranslationData> {
            override fun onFailure(call: Call<TranslationData>, t: Throwable) {
                translateText = "Throwable"
            }

            override fun onResponse(call: Call<TranslationData>, response: Response<TranslationData>) {
                translateText = "OPA"
            }
        })

        return translateText
    }
}