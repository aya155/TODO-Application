package com.chocolate_team.todo_application.util

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import com.chocolate_team.todo_application.R

class MyBroadcastReceiver: BroadcastReceiver() {
    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    private val channelId = "i.apps.notifications"
    override fun onReceive(p0: Context?, p1: Intent?) {
        notificationManager = p0!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val pendingIntent = PendingIntent.getActivity(p0, 0, p1, PendingIntent.FLAG_UPDATE_CURRENT)
        val title=p1!!.getStringExtra("Title").toString()
        val description=p1.getStringExtra("Description").toString()
        // checking if android version is greater than oreo(API 26) or not
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.GREEN
            notificationChannel.enableVibration(false)
            notificationManager.createNotificationChannel(notificationChannel)

            builder = Notification.Builder(p0, channelId)
                .setContentTitle(title)
                .setContentText(description)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentIntent(pendingIntent)
        } else {
            builder = Notification.Builder(p0)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(title)
                .setContentText(description)
                .setContentIntent(pendingIntent)
        }
        notificationManager.notify(1234, builder.build())
    }

}