package ua.volodymyr142.cryptomessenger

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ua.volodymyr142.cryptomessenger.core.di.coreAndroidModule
import ua.volodymyr142.cryptomessenger.core.di.coreModule
import ua.volodymyr142.cryptomessenger.di.sharedModule

class CryptoMessengerApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CryptoMessengerApplication)
            modules(coreModule, coreAndroidModule, sharedModule)
        }
    }
}
