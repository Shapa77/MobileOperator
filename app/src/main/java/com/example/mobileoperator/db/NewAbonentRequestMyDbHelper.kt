package com.example.mobileoperator.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class NewAbonentRequestMyDbHelper(context: Context): SQLiteOpenHelper(context, NewAbonentRequestDb.DATABASE_NAME,null, NewAbonentRequestDb.DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL(NewAbonentRequestDb.CREATE_TABLE)


    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

         db?.execSQL(NewAbonentRequestDb.SQL_DELETE_TABLE)
         onCreate(db)


    }


}