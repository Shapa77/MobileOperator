package com.example.mobileoperator.db

import android.provider.BaseColumns

object RegistrationDb : BaseColumns {

    const val TABLE_NAME = "RegistrationDb"
    const val COLUMN_NAME_NUMBER = "Number"
    const val COLUMN_NAME_PASSWORD = "Password"

          var DATABASE_VERSION = VersionDatabase.VERSION
    const val DATABASE_NAME = "MobileOperatorDb.db"

    const val CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY," +
            "$COLUMN_NAME_NUMBER TEXT, " +
            "$COLUMN_NAME_PASSWORD TEXT)"

    public const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"





}