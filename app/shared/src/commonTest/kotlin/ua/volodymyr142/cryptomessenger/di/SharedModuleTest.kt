package ua.volodymyr142.cryptomessenger.di

import org.koin.dsl.koinApplication
import ua.volodymyr142.cryptomessenger.AppViewModel
import ua.volodymyr142.cryptomessenger.core.di.coreModule
import kotlin.test.Test
import kotlin.test.assertNotNull

class SharedModuleTest {
    @Test
    fun `AppViewModel resolves through coreModule plus sharedModule`() {
        val koin =
            koinApplication {
                modules(coreModule, sharedModule)
            }.koin

        val viewModel = koin.get<AppViewModel>()

        assertNotNull(viewModel.symmetricCipher)
        koin.close()
    }
}
