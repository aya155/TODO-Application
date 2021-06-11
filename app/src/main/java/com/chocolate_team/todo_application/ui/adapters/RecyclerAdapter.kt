package com.chocolate_team.todo_application.ui.adapters

import com.chocolate_team.todo_application.ui.model.ItemHolderView


import android.app.Activity
import android.content.ContentValues
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.chocolate_team.todo_application.R
import com.chocolate_team.todo_application.databinding.FragmentHomeBinding
import com.chocolate_team.todo_application.ui.fragments.BaseFragment
import com.chocolate_team.todo_application.util.Constant


class RecyclerAdapter(private val dataList: List<ContentValues>, private val mContext: Activity) :
    RecyclerView.Adapter<ItemHolderView?>() {
    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolderView {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        return ItemHolderView(itemView)
    }

    override fun onBindViewHolder(holder: ItemHolderView, position: Int) {
        val s = dataList[position]
        holder.binding.apply {
            cardTitle.text= s.get(Constant.TITLE).toString()
            startTime.text= s.get(Constant.START_TIME).toString()
            endTime.text=s.get(Constant.END_TIME).toString()
            taskTime.text="${s.get(Constant.START_TIME)} - ${s.get(Constant.END_TIME)}"
//            quarterTimeline.background=ContextCompat.getDrawable(holder.itemView.context,)
        }
//        holder.binding.quarterTimeline.background = ResourcesCompat.getDrawable(mContext.resources,s.get(Constant.TITLE),null)

    }

    override fun getItemCount(): Int {
        return dataList.count()
    }


}