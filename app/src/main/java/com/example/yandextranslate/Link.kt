package com.example.yandextranslate

import retrofit2.Call
import retrofit2.http.*
import java.util.*

interface Link {
    @POST("/api/v1.5/tr.json/translate")
    fun translate(@Body data: TranslationData): Call<TranslationData>
}