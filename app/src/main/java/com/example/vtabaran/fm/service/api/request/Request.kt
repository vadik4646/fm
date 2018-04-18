package com.example.vtabaran.fm.service.api.request

import com.example.vtabaran.fm.User

abstract class Request {
    private var headers: MutableMap<String, String> = mutableMapOf()

    private var isPrivate: Boolean = true

    open var path: String = ""

    open var host: String = "http://10.0.2.2:8000/api/"

    open var method: String = "GET"

    abstract fun getBody(): String?

    companion object {
        const val AUTH_TOKEN_KEY: String = "X-AUTH-TOKEN"
    }

    fun getHeaders(): MutableMap<String, String> {
        val user = User.get()

        headers["Content-Type"] = "application/json"
        if (isPrivate && user != null) {
            headers[AUTH_TOKEN_KEY] = user.token
        }

        return headers
    }
}
