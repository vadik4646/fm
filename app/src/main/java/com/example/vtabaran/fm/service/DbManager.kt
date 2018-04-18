package com.example.vtabaran.fm.service

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.text.SimpleDateFormat
import java.util.*


class DbManager(context: Context, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int) :
        SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(db: SQLiteDatabase) {
        createCategoryTable(db)
        createCurrencyTable(db)
        createIncomeTable(db)
        createExpenseTable(db)
        createExpenseTagTable(db)
        createIncomeTagTable(db)
        createRateTable(db)
        createCustomizationTable(db)
        createIconTable(db)
        createTagTable(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }

    private fun createCategoryTable(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE category " +
                "( " +
                "  id         INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "  icon_id    INT          NULL, " +
                "  user_id    INT          NULL, " +
                "  name       VARCHAR(250) NOT NULL, " +
                "  created_at DATETIME     NOT NULL, " +
                "  updated_at DATETIME     NULL " +
                ") ")
    }

    private fun createCurrencyTable(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE currency " +
                "( " +
                "  code       VARCHAR(3)   NOT NULL " +
                "    PRIMARY KEY, " +
                "  name       VARCHAR(100) NOT NULL, " +
                "  created_at DATETIME     NOT NULL, " +
                "  updated_at DATETIME     NULL " +
                ") ")
    }

    private fun createIncomeTable(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE income " +
                "( " +
                "  id            INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "  user_id       INT        NULL, " +
                "  currency_code VARCHAR(3) NULL, " +
                "  category_id   INT        NULL, " +
                "  value         DOUBLE     NOT NULL, " +
                "  income_at     DATETIME   NOT NULL, " +
                "  created_at    DATETIME   NOT NULL, " +
                "  updated_at    DATETIME   NULL" +
                ") ")
    }

    private fun createExpenseTable(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE expense " +
                "( " +
                "  id            INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "  user_id       INT        NULL, " +
                "  currency_code VARCHAR(3) NULL, " +
                "  category_id   INT        NULL, " +
                "  value         DOUBLE     NOT NULL, " +
                "  spent_at      DATETIME   NOT NULL, " +
                "  created_at    DATETIME   NOT NULL, " +
                "  updated_at    DATETIME   NULL" +
                ") ")
    }

    private fun createExpenseTagTable(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE expense_tag " +
                "( " +
                "  expense_id INT NOT NULL, " +
                "  tag_id     INT NOT NULL, " +
                "  PRIMARY KEY (expense_id, tag_id)" +
                ") ")
    }

    private fun createIncomeTagTable(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE income_tag " +
                "( " +
                "  income_id INT NOT NULL, " +
                "  tag_id    INT NOT NULL" +
                ") ")
    }

    private fun createRateTable(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE rate " +
                "( " +
                "  id            INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "  currency_code VARCHAR(3) NULL, " +
                "  date          DATE       NOT NULL, " +
                "  value         DOUBLE     NOT NULL, " +
                "  created_at    DATETIME   NOT NULL, " +
                "  updated_at    DATETIME   NULL" +
                ") ")
    }

    private fun createCustomizationTable(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE customization " +
                "( " +
                "  id         INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "  user_id    INT          NULL, " +
                "  name       VARCHAR(50)  NOT NULL, " +
                "  value      VARCHAR(250) NOT NULL, " +
                "  created_at DATETIME     NOT NULL, " +
                "  updated_at DATETIME     NULL " +
                ") ")
    }

    private fun createIconTable(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE icon " +
                "( " +
                "  id    INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "  title VARCHAR(100) NOT NULL, " +
                "  class VARCHAR(100) NOT NULL " +
                ") ")
    }

    private fun createTagTable(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE tag " +
                "( " +
                "  id         INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,, " +
                "  user_id    INT          NULL, " +
                "  value      VARCHAR(255) NOT NULL, " +
                "  created_at DATETIME     NOT NULL, " +
                "  updated_at DATETIME     NULL " +
                ") ")
    }

    companion object {
        val SQL_DATE_FORMAT = SimpleDateFormat("YYYY-MM-dd HH:mm:ss", Locale.ENGLISH)
    }
}