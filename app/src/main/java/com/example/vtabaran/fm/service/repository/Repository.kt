package com.example.vtabaran.fm.service.repository

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.example.vtabaran.fm.service.DbManager
import com.example.vtabaran.fm.util.Entity
import com.example.vtabaran.fm.util.EntityConfiguration


abstract class Repository {
    abstract fun configuration() : EntityConfiguration

    fun findOneBy(
        whereClause: String? = null,
        whereArgs: Array<String>? = null,
        groupBy: String? = null,
        having: String? = null,
        order: String? = null,
        select: Array<String> = arrayOf("*")
    ) : Entity? {
        val connection = Repository.dbManager.writableDatabase
        val cursor = connection.query(configuration().getTableName(), select, whereClause, whereArgs, groupBy, having, order, "1")
        cursor.moveToFirst()
        val result = buildRow(cursor)
        cursor.close()

        return result
    }

    fun findBy(
        whereClause: String? = null,
        whereArgs: Array<String>? = null,
        limit: String? = null,
        order: String? = null,
        groupBy: String? = null,
        having: String? = null,
        select: Array<String> = arrayOf("*")
    ) : List<Entity> {
        val connection = Repository.dbManager.writableDatabase
        val cursor = connection.query(configuration().getTableName(), select, whereClause, whereArgs, groupBy, having, order, limit)

        val results: MutableList<Entity> = mutableListOf()
        while (cursor.moveToNext()) {
            results.add(buildRow(cursor)!!)
        }
        cursor.close()

        return results
    }

    fun delete(entity: Entity): Int {
        val connection = Repository.dbManager.writableDatabase

//        return connection.delete(configuration().getTableName(), "${configuration().getPkColumn()}=?", arrayOf(econfiguration().getPkColumn()))
        return 1
    }

    fun create(entity: Map<String, String>) : Long {
        val cv = ContentValues()
        val pkColumn = configuration().getPkColumn()

        for ((field, value) in entity) {
            if (field != pkColumn) {
                cv.put(field, value)
            }
        }

        return Repository.dbManager.writableDatabase.insert(configuration().getTableName(), null, cv)
    }

    fun update(entity: Entity): Int {
        val cv = ContentValues()

//        for ((field, value) in entity.toTableRow()) {
//            cv.put(field, value)
//        }

//        return Repository.dbManager.writableDatabase.update(configuration().getTableName(), cv, "${configuration().getPkColumn()}=?", arrayOf(entity.getPk()))
        return 1
    }

    private fun buildRow(cursor: Cursor): Entity? {
        val columnNames = cursor.columnNames
        val fields = mutableMapOf<String, Any?>()

        for (columnName in columnNames) {
            fields.put(columnName, buildCell(cursor, columnName))
        }

        return configuration().fillFromMap(fields)
    }

    private fun buildCell(cursor: Cursor, columnName: String): Any? {
        val columnIndex = cursor.getColumnIndex(columnName)
        val columnType = cursor.getType(columnIndex)

        when (columnType) {
            Cursor.FIELD_TYPE_STRING -> {
                return cursor.getString(columnIndex)
            }
            Cursor.FIELD_TYPE_INTEGER -> {
                return cursor.getInt(columnIndex)
            }
            Cursor.FIELD_TYPE_FLOAT -> {
                return cursor.getFloat(columnIndex)
            }
            Cursor.FIELD_TYPE_BLOB -> {
                return cursor.getBlob(columnIndex)
            }
        }

        return null
    }

    companion object {
        lateinit var dbManager: DbManager

        fun init(context: Context) {
             dbManager = DbManager(context, "finance_management", null, 1)
        }
    }
}