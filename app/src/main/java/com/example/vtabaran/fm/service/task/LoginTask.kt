package com.example.vtabaran.fm.service.task

import android.os.AsyncTask
import android.util.Log
import com.example.vtabaran.fm.User
import com.example.vtabaran.fm.service.api.ApiRequest
import com.example.vtabaran.fm.service.api.Response
import com.example.vtabaran.fm.service.api.request.LoginRequest
import com.example.vtabaran.fm.fragment.LoginFragment
import com.example.vtabaran.fm.util.UiHelper
import java.io.*
import java.net.MalformedURLException
import java.net.ProtocolException

class LoginTask constructor(private var loginRequest: LoginRequest) : AsyncTask<String, String, Response?>() {

    override fun doInBackground(vararg params: String): Response? {
        try {
            val request = ApiRequest(loginRequest)

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
            Log.d("LoginTask", response?.getMessage() ?: "Empty response")
            return
        }

        val token: String = response.getToken() ?: return
        val id: Int = response.getData()?.getInt("user_id") ?: return

        User.set(loginRequest.email, token, id)
        UiHelper.switchFragment(LoginFragment.newInstance())
    }
}