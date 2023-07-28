package studio.zebro.clipr.android.di

import org.koin.dsl.module
import studio.zebro.clipr.android.ServiceManagerImpl
import studio.zebro.clipr.platformSpecifics.ServiceManager

val myModule = module {
  single<ServiceManager> { ServiceManagerImpl() } // Declaration of Dependency
}