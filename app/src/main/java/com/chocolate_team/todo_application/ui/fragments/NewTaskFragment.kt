package com.chocolate_team.todo_application.ui.fragments

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.ContentValues
import android.content.Intent
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import com.chocolate_team.todo_application.databinding.FragmentNewTaskBinding
import com.chocolate_team.todo_application.util.Constant
import com.chocolate_team.todo_application.util.DbUtil
import com.chocolate_team.todo_application.util.MyBroadcastReceiver
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.SimpleDateFormat
import java.util.*

class NewTaskFragment: BaseFragment<FragmentNewTaskBinding>() {
    override val LOG_TAG: String="NEW_TASK_FRAGMENT"
    private val contentValues=ContentValues()
    override val bindingInflater: (LayoutInflater) -> FragmentNewTaskBinding=FragmentNewTaskBinding::inflate
   // set initial data in fragment (date ,time)
    override fun setup() {
        setDefault()
    }

    private fun setDefault() {
            val startTime=SimpleDateFormat("HH-mm", Locale.getDefault()).format(Date()).split('-')
            // set time current time
            setAndStoreTime(hour=startTime[0].toInt(), minute=startTime[1].toInt(),Constant.START_TIME)
         Calendar.getInstance().apply {
             // set date in after day
             setAndStoreDate(this.apply { add(Calendar.DATE ,1) }.timeInMillis)
             val endTime=SimpleDateFormat("HH-mm", Locale.getDefault()).format(this.apply {  add(Calendar.HOUR,1)}.time).split('-')
             setAndStoreTime(hour=endTime[0].toInt(), minute=endTime[1].toInt(),Constant.END_TIME)
         }
        }


    override fun addCallBack() {
        binding?.apply {
            closeBtn.setOnClickListener {
                backToHome()
            }
            setDateBtn.setOnClickListener {
                setDate()
            }
            setTimeBtn.setOnClickListener{
                setTime()
            }
            createTaskBtn.setOnClickListener {
                // create only if task has name
                takeIf{taskTitle.text.isNotBlank()}?.let{
                    // add title and if is remind or not
                    contentValues.apply {
                        put(Constant.TITLE,taskTitle.text.toString())
                        put(Constant.REMIND,remindSwitch.isChecked)
                        put(Constant.STATUS,false)
                    }
                    // save task in database
                    createTask()
                    // back to home activity
                    backToHome()
                }
            }
        }
    }

    private fun createTask() {
        // add task in database
        DbUtil.insertEntry(contentValues)
        Calendar.getInstance().apply {
            add(Calendar.DATE, -1)
            val dateString= SimpleDateFormat("yyyy-MM-dd").format(this.time).split('-')
            val c = "${dateString[0]}-${dateString[1]}-${dateString[2]}"
            Constant.tAdapter.setData(DbUtil.getEntries(c))
            takeIf { contentValues.get(Constant.REMIND)==true }?.let{
                createNotification(contentValues.get(Constant.TITLE).toString(),
                    "$c - ${contentValues.get(Constant.START_TIME)} - ${contentValues.get(Constant.END_TIME)}", contentValues.getAsString(Constant.START_TIME),contentValues.getAsString(Constant.DUE_DATE))
            }
       }
    }
    private fun backToHome(){
        // remove this fragment and back to home activity
        activity?.supportFragmentManager?.beginTransaction()?.remove(this@NewTaskFragment)?.commit()
    }

    private fun setTime(){
        timePicker(Constant.START_TIME)
    }
    private fun timePicker(time:String){
        MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_12H) // set 12 hour
            .setHour(12) // set initial hour
            .setMinute(0) // set initial minute
            .setTitleText("set Time") // set title in picker
            .build().apply {
                // when user click ok button
                addOnPositiveButtonClickListener{
                    setAndStoreTime(hour,minute,time)
                    takeIf { time==Constant.START_TIME }?.let { timePicker(Constant.END_TIME) }
                }
                show((this@NewTaskFragment.activity?.supportFragmentManager)!!,"TIME_PICKER")
            }
    }

    // set time in layout and store in contentValue
    private fun setAndStoreTime(hour:Int,minute:Int,time:String) {
        // set hour and minute just convert from 24 to 12 hour mode
        val calendar=Calendar.getInstance().apply {
            set(Calendar.HOUR, hour)
            set(Calendar.MINUTE, minute)
        }
        SimpleDateFormat("hh:mm a",Locale.getDefault()).format(calendar.time).apply {
            // show time in fragment
            binding?.taskTime?.text=if(time==Constant.END_TIME) binding?.taskTime?.text.toString() +this else "$this - "
            // store time in content Value
            contentValues.put(time,this)
        }
    }
     private fun setDate(){
         MaterialDatePicker.Builder.datePicker()
             .setTitleText("set Time") // set title
             .setSelection(MaterialDatePicker.todayInUtcMilliseconds()) // set initial day is current day
             .build().apply {
                 show((this@NewTaskFragment.activity?.supportFragmentManager)!!,"DATE_PICKER")
                 // when user click ok button picker
                 addOnPositiveButtonClickListener {
                     setAndStoreDate(it)
                 }
             }
     }
    private fun setAndStoreDate(date:Long){
        val calendar = Calendar.getInstance().also { it.time=Date(date) }
        val dayNum=calendar.get(Calendar.DAY_OF_MONTH)
        // show date in fragment
        binding?.taskDate?.text="${SimpleDateFormat("EEEE",Locale.getDefault()).format(date)} " + // get day name
                "$dayNum," +
                "${calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())}" // get month name
        // store date in content values
        val dateString=SimpleDateFormat("yyyy-MM-dd").format(calendar.time).split('-')
        contentValues.put(Constant.DUE_DATE,"${dateString[0]}-${dateString[1]}-${dateString[2]}")
    }

    override fun lightNightMode() {

    }
    private fun createNotification(taskTitle:String, descption:String, time:String,date:String) {
        val i= Intent(activity?.applicationContext,MyBroadcastReceiver::class.java)
        i.putExtra("Title",taskTitle)
        i.putExtra("Description",descption)
        val pi= PendingIntent.getBroadcast(activity?.applicationContext,111,i,0)
        val an=activity?.getSystemService(AppCompatActivity.ALARM_SERVICE) as AlarmManager
        val ttime=time.replace(':',' ').split(' ')
        val tdate=date.split('-')
        val s =Calendar.getInstance().apply {
            set(Calendar.MINUTE,ttime[1].toInt())
            set(Calendar.HOUR,ttime[0].toInt())
            set(Calendar.DAY_OF_MONTH,tdate[2].toInt())
            set(Calendar.MONTH,tdate[1].toInt()-1)
            set(Calendar.YEAR,tdate[0].toInt())
        }.timeInMillis
        an.set(AlarmManager.RTC_WAKEUP,s,pi)
        Toast.makeText(activity,"Done", Toast.LENGTH_LONG).show()
    }
}