package ua.volodymyr142.cryptomessenger.core.di

import org.koin.dsl.module
import ua.volodymyr142.cryptomessenger.core.crypto.SymmetricCipher

val coreModule =
    module {
        single { SymmetricCipher }
    }
