package com.example.yandextranslate

import androidx.test.InstrumentationRegistry

object TestFileUtils {
    fun readTestResourceFile(fileName: String): String {
        val fileInputStream = InstrumentationRegistry.getContext().assets.open(fileName).bufferedReader().use { it.readText() }
        return fileInputStream?.reader().toString()
    }
}