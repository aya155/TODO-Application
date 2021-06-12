package com.chocolate_team.todo_application.ui.adapters

import com.chocolate_team.todo_application.ui.model.ItemHolderView


import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.chocolate_team.todo_application.R
import com.chocolate_team.todo_application.databinding.FragmentHomeBinding
import com.chocolate_team.todo_application.ui.fragments.BaseFragment


class RecyclerAdapter(private val dataList: List<Int>, private val mContext: Activity) :
    RecyclerView.Adapter<ItemHolderView?>() {
    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolderView {
        val itemView = LayoutInflater.from(mContext).inflate(R.layout.item_row, parent, false)
        return ItemHolderView(itemView)
    }

    override fun onBindViewHolder(holder: ItemHolderView, position: Int) {
        val s = dataList[position]
//        holder.ivDivider.background = ResourcesCompat.getDrawable(mContext.resources,s,null)
    }

    override fun getItemCount(): Int {
        return dataList.count()
    }


}