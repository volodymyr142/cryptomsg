package ua.volodymyr142.cryptomessenger

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.koin.core.context.startKoin
import ua.volodymyr142.cryptomessenger.core.di.coreModule
import ua.volodymyr142.cryptomessenger.di.sharedModule

fun main() {
    startKoin {
        modules(coreModule, sharedModule)
    }
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "CryptoMessenger",
        ) {
            App()
        }
    }
}
