package com.chocolate_team.todo_application.ui.fragments

import android.view.LayoutInflater
import com.chocolate_team.todo_application.R
import com.chocolate_team.todo_application.databinding.FragmentHomeBinding


class HomeFragment: BaseFragment<FragmentHomeBinding>() {
    override val LOG_TAG: String="HOME_FRAGMENT"
    override val bindingInflater: (LayoutInflater) -> FragmentHomeBinding=FragmentHomeBinding::inflate

    override fun setup() {
    }

    override fun addCallBack() {
        binding?.btn?.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.add(R.id.fragment_container,NewTaskFragment())?.commit()
        }
    }
}