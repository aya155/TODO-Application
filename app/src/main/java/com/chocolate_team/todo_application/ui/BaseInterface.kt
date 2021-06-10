package com.chocolate_team.todo_application.ui

import android.app.Activity
import android.app.Application
import android.util.Log
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding

interface BaseInterface <VB:ViewBinding>{
    val LOG_TAG: String
    val bindingInflater: (LayoutInflater) -> VB
    var _binding: ViewBinding?
    var binding: VB?
        get() = _binding as VB?
        set(value) = TODO()
    val mContext: Activity?
    val appContext: Application?
    fun setup()
    fun addCallBack()
    fun lightNightMode()
    fun log(value: Any) { Log.v(LOG_TAG, value.toString()) }

}