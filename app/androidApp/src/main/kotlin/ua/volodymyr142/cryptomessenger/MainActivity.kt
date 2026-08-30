package ua.volodymyr142.cryptomessenger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import ua.volodymyr142.cryptomessenger.core.di.coreModule
import ua.volodymyr142.cryptomessenger.di.sharedModule

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            App()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    KoinApplication(application = { modules(coreModule, sharedModule) }) {
        App()
    }
}