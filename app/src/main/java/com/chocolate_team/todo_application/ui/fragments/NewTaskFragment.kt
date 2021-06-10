package com.chocolate_team.todo_application.ui.fragments

import android.app.Activity
import android.app.Application
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import com.chocolate_team.todo_application.databinding.FragmentNewTaskBinding

 class NewTaskFragment(override val mContext: Activity?, override val appContext: Application?) : BaseFragment<FragmentNewTaskBinding>() {
    override val LOG_TAG: String="NEW_TASK_FRAGMENT"
    override val bindingInflater: (LayoutInflater) -> FragmentNewTaskBinding=FragmentNewTaskBinding::inflate
    override fun setup() {
    }
    override fun addCallBack() {
    }

     override fun lightNightMode() {
         TODO("Not yet implemented")
     }
 }