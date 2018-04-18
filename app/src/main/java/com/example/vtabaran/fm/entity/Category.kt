package com.example.vtabaran.fm.entity

import com.example.vtabaran.fm.util.Entity
import java.util.*

class Category : Entity {
    var id: Int = 0
    var name: String = ""
    var icon: String = ""
    var createdAt: Date = Date()
    var updatedAt: Date = Date()
}