package com.example.yandextranslate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_first.*
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection
import java.net.InetAddress

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_first).setOnClickListener {


            val mockWebServer = MockWebServer()
            val ip = InetAddress.getByName("127.0.0.1")
            val port = 8080

            val myJson: String = context?.assets?.open("responseOne.json")?.bufferedReader().use {
                it?.readText()
                    .toString()
            }

            val response = MockResponse()
            response.setResponseCode(HttpURLConnection.HTTP_OK)

            response.setBody(myJson)

            mockWebServer.start(ip, port)

            val requestsToYandex = RequestsToYandex()
            requestsToYandex.myRequest(
                    text = enterText.text.toString(),
                    callback = object: Callback<TranslationData> {
                        override fun onFailure(call: Call<TranslationData>, t: Throwable) {
                            showTranslationError()
                        }

                        override fun onResponse(call: Call<TranslationData>, response: Response<TranslationData>) {
                            if (response.isSuccessful) {
                                val responseBody = response.body()
                                if (responseBody != null) {
                                    result.text = responseBody.text
                                } else {
                                    showTranslationError()
                                }
                            } else {
                                showTranslationError()
                            }
                        }
                    })
        }
    }

    private fun showTranslationError() {
        Toast.makeText(requireContext(), "Translation failed", Toast.LENGTH_SHORT).show()
    }
}
