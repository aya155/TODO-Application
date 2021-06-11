package com.chocolate_team.todo_application.util

import android.content.ContentValues
import android.content.Context
import com.chocolate_team.todo_application.data.domain.TodoDbHelper

object DbUtil {
    private lateinit var databaseHelper: TodoDbHelper
    // initial  create database
    fun initialDataBase(context: Context){
        databaseHelper= TodoDbHelper(context)
        databaseHelper.readableDatabase
    }
    // add data to database into tasks table
    fun insertEntry(entry: ContentValues){
        databaseHelper.writableDatabase.insert(Constant.TABLE_NAME,null,entry)
    }

    fun getEntries():ArrayList<ContentValues>{
        val cursor=databaseHelper.readableDatabase.rawQuery("SELECT * FROM ${Constant.TABLE_NAME}", arrayOf<String>())
        return ArrayList<ContentValues>().apply {
            while (cursor.moveToNext()){
                add(ContentValues().apply {
                    put(Constant.ID,cursor.getInt(0))
                    put(Constant.TITLE,cursor.getString(1))
                    put(Constant.START_TIME,cursor.getString(2))
                    put(Constant.END_TIME,cursor.getString(3))
                    put(Constant.DUE_DATE,cursor.getString(4))
                    put(Constant.REMIND,cursor.getInt(5))
                })
        }
        }
    }
}