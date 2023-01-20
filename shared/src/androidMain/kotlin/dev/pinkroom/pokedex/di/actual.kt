package dev.pinkroom.pokedex.di

import dev.pinkroom.pokedex.local.DatabaseDriverFactory
import io.ktor.client.engine.android.*
import org.koin.dsl.module

actual fun platformModule() = module {
    single { DatabaseDriverFactory(get()) }
    single { Android.create() }
}