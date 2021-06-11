package com.chocolate_team.todo_application.util

import com.chocolate_team.todo_application.ui.adapters.RecyclerAdapter

object Constant {
    const val REMIND="Remind"
    const val TABLE_NAME="Tasks"
    const val ID="id"
    const val TITLE="title"
    const val DUE_DATE="date"
    const val START_TIME="start_time"
    const val END_TIME="end_time"
    lateinit var tAdapter: RecyclerAdapter
}