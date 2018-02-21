package com.example.vtabaran.fm.api

import org.json.JSONObject

class Response constructor(body: String, private val code: Int, private val token: String?) {
    private var message: String? = null
    private var data: JSONObject? = null
    private var validationErrors: JSONObject? = null

    init {
        val json = JSONObject(body)

        if (json.has("message")) {
            message = json.getString("message")
        }
        if (json.has("data")) {
            data = json.getJSONObject("data")
        }

        if (json.has("validationErrors")) {
            validationErrors = json.getJSONObject("validationErrors")
        }
    }

    fun getMessage(): String? {
        return message
    }

    fun getToken(): String? {
        return token
    }

    fun getData(): JSONObject? {
        return data
    }

    fun getValidationErrors(): JSONObject? {
        return validationErrors
    }

    fun hasValidationErrors(): Boolean {
        return code == HTTP_BAD_REQUEST
    }

    fun hasErrors(): Boolean {
        return code != HTTP_OK
    }

    fun isUnauthorizedError(): Boolean {
        return code == HTTP_UNAUTHORIZED
    }

    fun isNotFoundError(): Boolean {
        return code == HTTP_NOT_FOUND
    }

    companion object {
        const val HTTP_OK = 200
        const val HTTP_NOT_FOUND = 404
        const val HTTP_BAD_REQUEST = 400
        const val HTTP_UNAUTHORIZED = 401
    }
}