package com.example.vtabaran.fm.task

import android.os.AsyncTask
import android.util.Log
import com.example.vtabaran.fm.User
import com.example.vtabaran.fm.api.ApiRequest
import com.example.vtabaran.fm.api.Response
import com.example.vtabaran.fm.api.request.RegisterRequest
import java.io.*
import java.net.MalformedURLException
import java.net.ProtocolException

class RegisterTask constructor(private var registerRequest: RegisterRequest): AsyncTask<String, String, Response>() {

    override fun doInBackground(vararg params: String): Response? {
        try {
            val request = ApiRequest(registerRequest)

            return request.send()
        } catch (e: ProtocolException) {
            e.printStackTrace()
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }

    override fun onPostExecute(response: Response?) {
        if (response == null || response.hasErrors()) {
            Log.d("Register Task", response?.getMessage() ?: "Empty response")
            return
        }

        val token: String = response.getToken() ?: return

        User.set(registerRequest.email, token)
    }

}