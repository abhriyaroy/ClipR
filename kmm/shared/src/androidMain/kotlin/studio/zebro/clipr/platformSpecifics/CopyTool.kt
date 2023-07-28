package studio.zebro.clipr.platformSpecifics

import org.koin.java.KoinJavaComponent.inject


actual class CopyTool {

  private val serviceManager: ServiceManager by inject(ServiceManager::class.java)

  actual fun showTool() {
    serviceManager.runService()
  }

}