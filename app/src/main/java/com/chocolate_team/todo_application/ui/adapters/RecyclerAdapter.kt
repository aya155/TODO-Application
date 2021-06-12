package com.chocolate_team.todo_application.ui.adapters



import android.content.ContentValues
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.chocolate_team.todo_application.R
import com.chocolate_team.todo_application.databinding.ItemRowBinding
import com.chocolate_team.todo_application.util.Constant


class RecyclerAdapter(private var dataList: List<ContentValues>,val listener: TaskInteractionListener) :
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
            status.setOnCheckedChangeListener { compoundButton, b ->
                listener.onchangeDoneBox(s,b)
            }
            status.isChecked= s.get(Constant.STATUS)==1
            takeIf {s.get(Constant.REMIND)==1}?.let { lineNoReminder.background=ContextCompat.getDrawable(holder.itemView.context,R.color.remind_color)}
            taskTime.text="${s.get(Constant.START_TIME)} - ${s.get(Constant.END_TIME)}"
        }

    }
    fun setData(newList:List<ContentValues>){
        val diffResult=DiffUtil.calculateDiff(TaskDiffUtil(dataList,newList))
        dataList=newList
        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemCount()= dataList.size
}