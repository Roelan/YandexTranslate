package com.example.yandextranslate

import retrofit2.Call
import retrofit2.http.*
import java.util.*

interface Link {
    @POST("./")
    fun translate(@Body data: TranslationData): Call<TranslationData>
}