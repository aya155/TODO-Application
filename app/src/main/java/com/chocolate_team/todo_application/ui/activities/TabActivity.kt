package com.chocolate_team.todo_application.ui.activities

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import com.chocolate_team.todo_application.databinding.ActivityTabBinding
import com.chocolate_team.todo_application.ui.fragments.HomeFragment
import com.chocolate_team.todo_application.util.PrefsUtil


class TabActivity : BaseActivity<ActivityTabBinding>() {

    override val LOG_TAG: String="TAB_ACTIVITY"
    override val bindingInflater: (LayoutInflater) -> ActivityTabBinding=ActivityTabBinding::inflate

    override fun setup() {
        PrefsUtil.initialPrefs(applicationContext)
        addFragment(HomeFragment())
    }

    override fun addCallBack() {
    }

     private fun addFragment(fragment: Fragment) {
        binding?.let { supportFragmentManager.beginTransaction().add(it.fragmentContainer.id,fragment).commit() }
    }

    override fun lightNightMode() {
    }


}
