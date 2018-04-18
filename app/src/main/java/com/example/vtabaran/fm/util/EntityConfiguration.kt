package com.example.vtabaran.fm.util

interface EntityConfiguration {
    fun toTableRow(entityRow: Any): Map<String, String>

    fun getPkColumn(): String

    fun fillFromMap(rowValues: MutableMap<String, Any?>) : Entity

    fun getTableName(): String
}