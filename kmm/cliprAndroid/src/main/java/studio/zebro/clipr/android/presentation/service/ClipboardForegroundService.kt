package studio.zebro.clipr.android.presentation.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.pm.ServiceInfo
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import studio.zebro.clipr.android.R

class ClipboardForegroundService : BaseService() {

  private lateinit var clipboardManager: ClipboardManager

  override fun onCreate() {
    super.onCreate()
    clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
  }

  override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
      startForeground(NOTIFICATION_ID, createNotification(), ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC)
    } else {
      startForeground(NOTIFICATION_ID, createNotification())
    }
    return START_STICKY
  }

  private fun createNotification(): Notification {
    val notificationChannelId = "clipboard_service_channel"
    val notificationChannelName = "ClipR clipboard service"
    val notificationChannelDescription = "ClipR clipboard service to copy past items from foreground service"

    val importance = NotificationManager.IMPORTANCE_LOW
    val channel = NotificationChannel(notificationChannelId, notificationChannelName, importance).apply {
      description = notificationChannelDescription
    }
    // Register the channel with the system
    val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    notificationManager.createNotificationChannel(channel)

    // Define actions
    val copyToAppIntent = Intent(this, ClipboardForegroundService::class.java).apply {
      action = ACTION_COPY_TO_APP
    }
    val copyToClipboardIntent = Intent(this, ClipboardForegroundService::class.java).apply {
      action = ACTION_COPY_TO_CLIPBOARD
    }

    val copyToAppPendingIntent =
      PendingIntent.getService(this, 0, copyToAppIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
    val copyToClipboardPendingIntent =
      PendingIntent.getService(this, 1, copyToClipboardIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

    val notificationBuilder = NotificationCompat.Builder(this, notificationChannelId)
      .setContentTitle("Clipboard Service")
      .setContentText("Manage clipboard actions. Press \'Copy to ClipR\' to copy current clipboard items to ClipR and share to other devices and click \'Paste from ClipR\' to paste over items from CLipR to current device clipboard")
      .setSmallIcon(studio.zebro.clipr.R.drawable.ic_clipr_main) // Replace with your icon
      .addAction(R.drawable.ic_copy, "Copy to ClipR", copyToAppPendingIntent)
      .addAction(R.drawable.ic_paste, "Paste from ClipR", copyToClipboardPendingIntent)

    return notificationBuilder.build()
  }

  override fun onBind(intent: Intent?): IBinder? {
    return null
  }

  companion object {
    const val NOTIFICATION_ID = 101
    const val ACTION_COPY_TO_APP = "action_copy_to_app"
    const val ACTION_COPY_TO_CLIPBOARD = "action_copy_to_clipboard"

    fun startService(context: Context) {
      if (isServiceRunningInForeground(context, ClipboardForegroundService::class.java)) {
        return
      }
      val startIntent = Intent(context, ClipboardForegroundService::class.java)
      context.startService(startIntent)
    }

    fun stopService(context: Context) {
      if (!isServiceRunningInForeground(context, ClipboardForegroundService::class.java)) {
        return
      }
      val stopIntent = Intent(context, ClipboardForegroundService::class.java)
      context.stopService(stopIntent)
    }
  }
}
