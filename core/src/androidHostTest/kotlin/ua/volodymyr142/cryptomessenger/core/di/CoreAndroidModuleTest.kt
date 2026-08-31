package ua.volodymyr142.cryptomessenger.core.di

import android.content.Context
import org.koin.test.verify.verify
import kotlin.test.Test

class CoreAndroidModuleTest {
    @Test
    fun `coreAndroidModule resolves against the Context Koin's androidContext supplies`() {
        // Context is bound by androidContext(...) at startup, not by this module — declared here
        // as an extraType since verify() checks one module's definitions at a time.
        coreAndroidModule.verify(extraTypes = listOf(Context::class))
    }
}
