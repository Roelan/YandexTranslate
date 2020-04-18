package com.example.yandextranslate

import android.os.Bundle
import android.renderscript.RSDriverException
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_first.*
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
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
            result.text = requestsToYandex.myRequest(text = enterText.text.toString())
        }
    }
}
