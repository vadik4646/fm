package com.example.vtabaran.fm.entity

import com.example.vtabaran.fm.util.Entity
import java.util.*

class Income: Entity {
    var id: Int = 0
    var value: Double = 0.0
    var userId: Int = 0
    var incomeAt: Date = Date()
    var createdAt: Date = Date()
    var currencyCode: String = ""
    var categoryId: Int = 0
    var tags: Collection<Tag> = listOf()
}