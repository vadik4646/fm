package com.example.vtabaran.fm.entity

import com.example.vtabaran.fm.util.Entity
import java.util.*

class Tag : Entity {
    var id: Int = 0
    var value: String = ""
    var createdAt: Date = Date()
    var updatedAt: Date = Date()
    var userId: Int = 0
}