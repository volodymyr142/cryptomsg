package ua.volodymyr142.cryptomessenger.core.di

import org.koin.dsl.module

// Context itself is bound by Koin's androidContext(...) call at startup, not here.
// Keystore-backed providers that need it land in this module in a later ticket.
val coreAndroidModule = module {}
