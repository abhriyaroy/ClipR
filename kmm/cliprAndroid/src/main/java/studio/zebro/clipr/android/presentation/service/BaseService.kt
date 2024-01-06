package studio.zebro.clipr.android.presentation.service

import android.app.ActivityManager
import android.app.Service
import android.content.Context

abstract class BaseService : Service() {

  companion object{
    fun isServiceRunningInForeground(context: Context, serviceClass: Class<*>): Boolean {
      val manager: ActivityManager =
        context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
      for (service in manager.getRunningServices(Int.MAX_VALUE)) {
        if (serviceClass.name == service.service.className) {
          if (service.foreground) {
            return true
          }
        }
      }
      return false
    }
  }
}