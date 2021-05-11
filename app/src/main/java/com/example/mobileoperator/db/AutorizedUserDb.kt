package com.example.mobileoperator.db

import android.provider.BaseColumns

object AutorizedUserDb : BaseColumns {

    const val TABLE_NAME = "AutorizedUserDb"
    const val COLUMN_NAME_NAME = "Name"
    const val COLUMN_NAME_SURNAME = "Surname"
    const val COLUMN_NAME_NUMBER = "Number"
    const val COLUMN_NAME_PASSWORD = "Password"
    const val COLUMN_NAME_TARIFF = "Tariff"
    const val COLUMN_NAME_BALANCE = "Balance"



    var DATABASE_VERSION = VersionDatabase.VERSION
    const val DATABASE_NAME = "MobileOperatorDb.db"

    const val CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY," +
            "$COLUMN_NAME_NAME TEXT, " +
            "$COLUMN_NAME_SURNAME TEXT, " +
            "$COLUMN_NAME_NUMBER TEXT, " +
            "$COLUMN_NAME_PASSWORD TEXT, " +
            "$COLUMN_NAME_TARIFF TEXT, " +
            "$COLUMN_NAME_BALANCE TEXT)"

    public const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"



}