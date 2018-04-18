package com.example.vtabaran.fm.entity

import com.example.vtabaran.fm.util.Entity
import java.util.*

class Currency : Entity {
    var code: String = ""
    var name: String = ""
    var createdAt: Date = Date()
    var updatedAt: Date = Date()
    var symbol: String = ""
}