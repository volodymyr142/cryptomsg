package ua.volodymyr142.cryptomessenger

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "CryptoMessenger",
    ) {
        App()
    }
}