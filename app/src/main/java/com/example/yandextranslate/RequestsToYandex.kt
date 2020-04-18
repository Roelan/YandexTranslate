package com.example.yandextranslate

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RequestsToYandex {

    private val gson: Gson = GsonBuilder().create()

    val yandexUrl = "http://127.0.0.1:8080"

    val KEY = "trnsl.1.1.20200413T221437Z.155bf65b20a757c5.5ebf2c44c876f664e5f2e75f5fe5d4d72ca90f83"

    fun myRequest(text: String, callback: Callback<TranslationData>) {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(yandexUrl)
            .build()

        val api: Link = retrofit.create(Link::class.java)
        val myData = TranslationData(KEY, text, "en-ru")

        val call = api.translate(myData)

        call.enqueue(callback)
    }
}