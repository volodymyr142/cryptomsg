package ua.volodymyr142.cryptomessenger

import androidx.compose.ui.window.ComposeUIViewController
import org.koin.compose.KoinApplication
import ua.volodymyr142.cryptomessenger.core.di.coreModule
import ua.volodymyr142.cryptomessenger.di.sharedModule

fun MainViewController() = ComposeUIViewController {
    KoinApplication(application = { modules(coreModule, sharedModule) }) {
        App()
    }
}