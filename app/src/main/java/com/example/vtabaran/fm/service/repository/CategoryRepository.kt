package com.example.vtabaran.fm.service.repository

import com.example.vtabaran.fm.entity.Category
import com.example.vtabaran.fm.service.DbManager
import com.example.vtabaran.fm.util.EntityConfiguration

class CategoryRepository {
    companion object: EntityConfiguration {
        override fun toTableRow(entityRow: Any): Map<String, String> {
            entityRow as Category

            return mapOf(
                "id" to entityRow.id.toString(),
                "name" to entityRow.name,
                "icon" to entityRow.icon,
                "created_at" to DbManager.SQL_DATE_FORMAT.format(entityRow.createdAt),
                "updated_at" to DbManager.SQL_DATE_FORMAT.format(entityRow.updatedAt)
            )
        }

        override fun getTableName(): String {
            return "income"
        }

        override fun fillFromMap(rowValues: MutableMap<String, Any?>) : Category {
            val category = Category()

            category.id = rowValues["id"].toString().toInt()
            category.name = rowValues["name"].toString()
            category.icon = rowValues["icon"].toString()
            category.createdAt = DbManager.SQL_DATE_FORMAT.parse(rowValues["created_at"].toString())
            category.updatedAt = DbManager.SQL_DATE_FORMAT.parse(rowValues["updated_at"].toString())

            return category
        }

        override fun getPkColumn(): String {
            return "id"
        }
    }
}