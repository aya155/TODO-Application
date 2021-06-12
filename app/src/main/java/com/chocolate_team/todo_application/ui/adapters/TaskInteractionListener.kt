package com.chocolate_team.todo_application.ui.adapters

import android.content.ContentValues

interface TaskInteractionListener {
    fun onchangeDoneBox(task:ContentValues,checked: Boolean)
}