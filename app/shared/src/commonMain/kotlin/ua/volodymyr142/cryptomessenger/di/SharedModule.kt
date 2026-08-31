package ua.volodymyr142.cryptomessenger.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import ua.volodymyr142.cryptomessenger.AppViewModel

val sharedModule =
    module {
        viewModelOf(::AppViewModel)
    }
