package com.chocolate_team.todo_application.ui.fragments

import android.content.ContentValues
import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import android.widget.Switch
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.chocolate_team.todo_application.R
import com.chocolate_team.todo_application.databinding.FragmentHomeBinding
import com.chocolate_team.todo_application.ui.adapters.RecyclerAdapter
import com.chocolate_team.todo_application.ui.adapters.TaskInteractionListener
import com.chocolate_team.todo_application.util.Constant
import com.chocolate_team.todo_application.util.DbUtil
import java.text.SimpleDateFormat
import java.util.*


class HomeFragment : BaseFragment<FragmentHomeBinding>() ,TaskInteractionListener{
    override val LOG_TAG: String="HOME_FRAGMENT"
    override val bindingInflater: (LayoutInflater) -> FragmentHomeBinding=FragmentHomeBinding::inflate


    override fun setup() {
        //Recycler View
//        val recyclerView: RecyclerView = binding!!.recycleViewItems
        //Data
        Calendar.getInstance().apply {
            add(Calendar.DATE, -1)
            val dateString= SimpleDateFormat("yyyy-MM-dd").format(this.time).split('-')
            val c = "${dateString[0]}-${dateString[1]}-${dateString[2]}"
            log(c)
            val dataTasks = DbUtil.getEntries(c)

            //Recycler View Adapter
            Constant.tAdapter = activity?.let { RecyclerAdapter(dataTasks,this@HomeFragment) }!!
            binding?.apply {
                recycleViewItems.apply {
                    itemAnimator = DefaultItemAnimator()
                    adapter = Constant.tAdapter
                }
                Calendar.getInstance().apply {
                    this.time as Date
                    dayText.text="${SimpleDateFormat("EEEE",Locale.getDefault()).format(this.time as Date)}"
                    dayDateText.text=this.get(Calendar.DAY_OF_MONTH).toString()+"th"
                }
            }
//            recyclerView.itemAnimator = DefaultItemAnimator()
//            recyclerView.adapter = Constant.tAdapter
        }
    }

    override fun addCallBack() {
        binding?.apply {
            // create new task
            newTaskButton.setOnClickListener{
                activity?.supportFragmentManager?.beginTransaction()?.add(R.id.fragment_container,NewTaskFragment())?.commit()
            }
            // change theme app
            lightDarkImage.setOnClickListener{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }

    }

    override fun lightNightMode() {}
    override fun onchangeDoneBox(task: ContentValues, checked: Boolean) {
            task.put(Constant.STATUS,checked)
            DbUtil.updateStatus(task)
    }

}