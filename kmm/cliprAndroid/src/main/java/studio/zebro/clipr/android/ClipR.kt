package studio.zebro.clipr.android

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import studio.zebro.clipr.di.sharedModule

class ClipR : Application() {

  override fun onCreate() {
    super.onCreate()
    initDi()
  }

  private fun initDi() {
    startKoin {
      androidLogger()
      androidContext(this@ClipR)
      modules(
        sharedModule
      )
    }
  }
}