package com.chocolate_team.todo_application.ui.activities

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import androidx.viewbinding.ViewBinding
import com.chocolate_team.todo_application.R
import com.chocolate_team.todo_application.ui.BaseInterface

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity(), BaseInterface<VB> {
    override var _binding: ViewBinding?=null
    override var binding: VB?
        get() = _binding as VB?
        set(value) = TODO()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        _binding = bindingInflater(layoutInflater)
        setContentView(requireNotNull(_binding).root)
        setup()
        addCallBack()
        lightNightMode()
    }
    override fun lightNightMode() {
        val dark = findViewById<SwitchCompat>(R.id.lightDarkImage)
        val sharedPreferences : SharedPreferences = getSharedPreferences("sharedpreference",
            Context.MODE_PRIVATE)
        val sharedprefEdit : SharedPreferences.Editor = sharedPreferences.edit()
        var darkMode = sharedPreferences.getBoolean("darkMode",false)
        if (darkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        dark.setOnClickListener {
            if(darkMode){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                sharedprefEdit.putBoolean("darkMode",false)
                sharedprefEdit.apply()
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                sharedprefEdit.putBoolean("darkMode",true)
                sharedprefEdit.apply()
            }
        }
    }
}
