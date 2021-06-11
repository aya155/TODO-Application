package com.chocolate_team.todo_application.ui.fragments

import android.view.LayoutInflater
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.chocolate_team.todo_application.R
import com.chocolate_team.todo_application.databinding.FragmentHomeBinding
import com.chocolate_team.todo_application.ui.adapters.RecyclerAdapter
import java.util.*


class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val LOG_TAG: String="HOME_FRAGMENT"
    override val bindingInflater: (LayoutInflater) -> FragmentHomeBinding=FragmentHomeBinding::inflate


    override fun setup() {
        //Recycler View
        val recyclerView: RecyclerView = binding!!.recycleViewItems
        //Data
        val dataList: MutableList<Int> = ArrayList()
        dataList.add(R.drawable.quarter_timeline)
        dataList.add(R.drawable.quarter_timeline)
        dataList.add(R.drawable.quarter_timeline)
        dataList.add(R.drawable.quarter_timeline)


        //Recycler View Adapter
        val mAdapter = activity?.let { RecyclerAdapter(dataList, it) }
        val mLayoutManager: RecyclerView.LayoutManager =
            LinearLayoutManager(activity?.applicationContext)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = mAdapter
    }

    override fun addCallBack() {
    }

    override fun lightNightMode() {}

}