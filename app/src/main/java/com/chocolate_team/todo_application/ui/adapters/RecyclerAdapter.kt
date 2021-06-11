package com.chocolate_team.todo_application.ui.adapters



import android.app.Activity
import android.content.ContentValues
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.chocolate_team.todo_application.R
import com.chocolate_team.todo_application.databinding.FragmentHomeBinding
import com.chocolate_team.todo_application.databinding.ItemRowBinding
import com.chocolate_team.todo_application.ui.fragments.BaseFragment
import com.chocolate_team.todo_application.util.Constant


class RecyclerAdapter(private var dataList: List<ContentValues>) :
    RecyclerView.Adapter<RecyclerAdapter.ItemHolderView>() {

    class ItemHolderView(view: View) : RecyclerView.ViewHolder(view) {
        var binding= ItemRowBinding.bind(view)

    }
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
    fun setData(newList:List<ContentValues>){
        val diffResult=DiffUtil.calculateDiff(TaskDiffUtil(dataList,newList))
        dataList=newList
        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int {
        return dataList.count()
    }


}