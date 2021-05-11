package com.example.mobileoperator.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AutorizedUserMuDbHelper (context: Context): SQLiteOpenHelper(context, AutorizedUserDb.DATABASE_NAME,null, AutorizedUserDb.DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL(AutorizedUserDb.CREATE_TABLE)


    }

    override fun onUpgrade(db: SQLiteDatabase? ,oldVersion: Int ,newVersion: Int) {

        db?.execSQL(AutorizedUserDb.SQL_DELETE_TABLE)
        onCreate(db)

    }
}