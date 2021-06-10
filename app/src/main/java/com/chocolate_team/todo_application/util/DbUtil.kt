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

}