package ua.volodymyr142.cryptomessenger.di

import org.koin.test.verify.verify
import ua.volodymyr142.cryptomessenger.core.crypto.SymmetricCipher
import kotlin.test.Test

class SharedModuleVerifyTest {
    @Test
    fun `sharedModule resolves against coreModule's bindings`() {
        // SymmetricCipher is provided by coreModule, not sharedModule itself — declared here as
        // an extraType since verify() checks one module's definitions at a time.
        sharedModule.verify(extraTypes = listOf(SymmetricCipher::class))
    }
}
