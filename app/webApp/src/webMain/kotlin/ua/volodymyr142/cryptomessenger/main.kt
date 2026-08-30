package ua.volodymyr142.cryptomessenger

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import org.koin.core.context.startKoin
import ua.volodymyr142.cryptomessenger.core.di.coreModule
import ua.volodymyr142.cryptomessenger.di.sharedModule

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    startKoin {
        modules(coreModule, sharedModule)
    }
    ComposeViewport {
        App()
    }
}