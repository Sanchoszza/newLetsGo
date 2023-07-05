package com.android.letsgo.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.graphics.drawable.IconCompat
import com.android.letsgo.activity.AdsActivity

class NotificationHelper {


    companion object{
        val CHANEL_ID = "id"
        val CHANEL_NAME = "name"
        val CHANEL_DESCRIPTION = "descript"

        fun showNotification(context: Context, title: String?, message: String?, image: String?) {
            val intent = Intent(context, AdsActivity::class.java)
            val pendingIntent =
                PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
            val builder = NotificationCompat.Builder(context, CHANEL_ID)
                .setSmallIcon(IconCompat.createWithContentUri(image!!))
                .setContentTitle(title)
                .setContentText(message)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel =
                    NotificationChannel(CHANEL_ID, CHANEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
                channel.description = CHANEL_DESCRIPTION
                notificationManager.createNotificationChannel(channel)
            }
        }
    }

}