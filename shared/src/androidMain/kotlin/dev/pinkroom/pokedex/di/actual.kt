package dev.pinkroom.pokedex.di

import io.ktor.client.engine.android.*
import org.koin.dsl.module

actual fun platformModule() = module {
    single { Android.create() }
}