package com.chocolate_team.todo_application.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.chocolate_team.todo_application.ui.BaseInterface

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity(), BaseInterface<VB> {
    override var _binding: ViewBinding?=null
    override var binding: VB?
        get() = _binding as VB?
        set(value) = TODO()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingInflater(layoutInflater)
        setContentView(requireNotNull(_binding).root)
        setup()
        addCallBack()
        lightNightMode()
    }
}
