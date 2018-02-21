package com.example.vtabaran.fm.api.request

import org.json.JSONObject

class RegisterRequest constructor(val email: String, private val password: String): Request() {
    override var path = "register"

    override var method = "POST"

    override fun getBody(): String {
        val jsonRequest = JSONObject()

        jsonRequest.put("email", email)
        jsonRequest.put("password", password)

        return jsonRequest.toString()
    }
}