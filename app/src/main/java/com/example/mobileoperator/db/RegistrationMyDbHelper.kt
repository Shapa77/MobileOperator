package com.example.mobileoperator.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class RegistrationMyDbHelper(context: Context): SQLiteOpenHelper(context, RegistrationDb.DATABASE_NAME,null, RegistrationDb.DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL(RegistrationDb.CREATE_TABLE)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        db?.execSQL(RegistrationDb.SQL_DELETE_TABLE)
        onCreate(db)


    }




}