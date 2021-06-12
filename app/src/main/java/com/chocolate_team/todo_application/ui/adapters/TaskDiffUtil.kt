package com.chocolate_team.todo_application.ui.adapters

import android.content.ContentValues
import androidx.recyclerview.widget.DiffUtil
import com.chocolate_team.todo_application.util.Constant

class TaskDiffUtil(val tOldList: List<ContentValues>,val tNewList:List<ContentValues>) :DiffUtil.Callback() {
    override fun getOldListSize() = tOldList.size
    override fun getNewListSize()=tNewList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int)=tOldList[oldItemPosition].get(Constant.ID)==tNewList[newItemPosition].get(Constant.ID)

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int)=true
}