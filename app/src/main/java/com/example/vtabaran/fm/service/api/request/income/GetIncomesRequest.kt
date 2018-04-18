package com.example.vtabaran.fm.service.api.request.income

import com.example.vtabaran.fm.service.api.request.Request

class GetIncomesRequest : Request() {
    override var path = "income"

    override var method = "GET"

    override fun getBody(): String? {
        return null
    }
}