package dev.pinkroom.pokedex.di

import dev.pinkroom.pokedex.local.DatabaseDriverFactory
import io.ktor.client.engine.darwin.*
import org.koin.dsl.module

actual fun platformModule() = module {
    single { DatabaseDriverFactory() }
    single { Darwin.create() }
}