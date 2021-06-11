package com.chocolate_team.todo_application.ui.fragments

import android.content.ContentValues
import android.view.LayoutInflater
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chocolate_team.todo_application.R
import com.chocolate_team.todo_application.databinding.FragmentHomeBinding
import com.chocolate_team.todo_application.ui.adapters.RecyclerAdapter
import com.chocolate_team.todo_application.util.DbUtil
import java.util.ArrayList


class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val LOG_TAG: String="HOME_FRAGMENT"
    override val bindingInflater: (LayoutInflater) -> FragmentHomeBinding=FragmentHomeBinding::inflate


    override fun setup() {
        //Recycler View
        val recyclerView: RecyclerView = binding!!.recycleViewItems

        //Data
        val dataTasks= DbUtil.getEntries()

        //Recycler View Adapter
        val mAdapter = activity?.let { RecyclerAdapter(dataTasks, it) }
//        val mLayoutManager: RecyclerView.LayoutManager =
//            LinearLayoutManager(activity?.applicationContext)
//        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = mAdapter
    }

    override fun addCallBack() {
        binding?.backButton?.setOnClickListener{
            activity?.supportFragmentManager?.beginTransaction()?.add(R.id.fragment_container,NewTaskFragment())?.commit()
        }
    }

    override fun lightNightMode() {}

}