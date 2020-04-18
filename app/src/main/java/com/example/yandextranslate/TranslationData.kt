package com.example.yandextranslate

import com.google.gson.annotations.SerializedName

data class TranslationData(
    @SerializedName("key")
    var key: String,
    @SerializedName("lang")
    var lang: String,
    @SerializedName("text")
    var text: String
)