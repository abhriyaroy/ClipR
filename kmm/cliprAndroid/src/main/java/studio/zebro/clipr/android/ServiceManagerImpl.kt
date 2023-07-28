package studio.zebro.clipr.android

import android.util.Log
import studio.zebro.clipr.platformSpecifics.ServiceManager

class ServiceManagerImpl : ServiceManager {
  override fun runService() {
    Log.d("ServiceManagerImpl", "runService")
  }
}