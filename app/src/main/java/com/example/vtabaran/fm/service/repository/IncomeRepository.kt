package com.example.vtabaran.fm.service.repository

import com.example.vtabaran.fm.User
import com.example.vtabaran.fm.entity.Income
import com.example.vtabaran.fm.service.DbManager
import com.example.vtabaran.fm.util.Entity
import com.example.vtabaran.fm.util.EntityConfiguration

class IncomeRepository : Repository() {
    override fun configuration(): EntityConfiguration {
        return IncomeRepository
    }

    fun findByUser(offset: Int, count: Int) : List<Income> {
        val userId = User.get()?.id ?: 0

        val entities = findBy("user_id=?", arrayOf(userId.toString()), "$offset, $count")

        return buildEntityList(entities)
    }

    private fun buildEntityList(entities: List<Entity>) : List<Income> {
        return entities.map { it as Income }
    }

    companion object: EntityConfiguration {
        override fun toTableRow(entityRow: Any): Map<String, String> {
            entityRow as Income
            return mapOf(
                "value" to entityRow.value.toString(),
                "user_id" to entityRow.userId.toString(),
                "income_at" to DbManager.SQL_DATE_FORMAT.format(entityRow.incomeAt),
                "currency_code" to entityRow.currencyCode,
                "category_id" to entityRow.categoryId.toString(),
                "created_at" to DbManager.SQL_DATE_FORMAT.format(entityRow.createdAt)
            )
        }

        override fun getTableName(): String {
            return "income"
        }

        override fun fillFromMap(rowValues: MutableMap<String, Any?>) : Income {
            val income = Income()

            income.id = rowValues["id"].toString().toInt()
            income.value = rowValues["value"].toString().toDouble()
            income.incomeAt = DbManager.SQL_DATE_FORMAT.parse(rowValues["income_at"].toString())
            income.createdAt = DbManager.SQL_DATE_FORMAT.parse(rowValues["created_at"].toString())
            income.categoryId = rowValues["category_id"].toString().toInt()
            income.currencyCode = rowValues["currency_code"].toString()

            return income
        }

        override fun getPkColumn(): String {
            return "id"
        }
    }
}