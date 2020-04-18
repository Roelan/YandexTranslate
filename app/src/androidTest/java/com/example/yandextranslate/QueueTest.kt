package com.example.yandextranslate

import okhttp3.HttpUrl
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.InetAddress

class QueueTest {

    val mockServer = MockWebServer()

    @Before
    fun initMockServer() {
        //mockServer.enqueue(MockResponse().setBody("1st message"))
        val response: MockResponse = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(TestFileUtils.readTestResourceFile("responseOne.json"))
        mockServer.enqueue(response)

    }

    @After
    fun after() {
        mockServer.shutdown()

    }

    @Test
    fun queueTest() {
        mockServer.start(8080)
      //  val basUrl: HttpUrl = mockServer.
        Thread.sleep(20000)
    }
}