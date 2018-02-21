package com.example.vtabaran.fm.api

import com.example.vtabaran.fm.api.request.Request
import java.io.*
import java.net.URL
import java.net.HttpURLConnection

class ApiRequest constructor(private val request: Request) {

    fun send(): Response {
        val connection = connect()
        val out = OutputStreamWriter(connection.outputStream)

        out.write(request.getBody())
        out.close()

        return getResponse(connection)
    }

    private fun connect(): HttpURLConnection
    {
        val url = URL(request.host + request.path)
        val connection = url.openConnection() as HttpURLConnection

        for ((headerKey: String, headerValue: String) in request.getHeaders()) {
            connection.setRequestProperty(headerKey, headerValue)
        }

        connection.setRequestProperty("Content-Type", "application/json")
        connection.requestMethod = request.method
        connection.connect()

        return connection
    }

    private fun getResponse(connection: HttpURLConnection): Response {
        val statusCode = connection.responseCode
        val token = connection.getHeaderField(Request.AUTH_TOKEN_KEY)
        val stream = getStream(connection, statusCode)
        val body = read(stream)

        return Response(body, statusCode, token)
    }

    private fun getStream(connection: HttpURLConnection, statusCode: Int): InputStream {
        return if (statusCode != Response.HTTP_OK) {
            BufferedInputStream(connection.errorStream)
        } else {
            BufferedInputStream(connection.inputStream)
        }
    }

    private fun read(stream: InputStream): String {
        val read = InputStreamReader(stream)
        val buffer = BufferedReader(read)
        val data = StringBuilder()
        var chunks = buffer.readLine()
        while (chunks != null) {
            data.append(chunks)
            chunks = buffer.readLine()
        }

        return data.toString()
    }

}