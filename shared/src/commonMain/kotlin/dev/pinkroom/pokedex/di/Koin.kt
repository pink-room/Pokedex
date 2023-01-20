package dev.pinkroom.pokedex.di

import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.create
import dev.pinkroom.pokedex.PokemonDatabase
import dev.pinkroom.pokedex.data.repository.DetailsRepository
import dev.pinkroom.pokedex.data.repository.PokedexRepository
import dev.pinkroom.pokedex.data.service.DetailsService
import dev.pinkroom.pokedex.data.service.PokedexService
import dev.pinkroom.pokedex.local.DatabaseDriverFactory
import dev.pinkroom.pokedex.local.PokemonDao
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

expect fun platformModule(): Module

fun initKoin(enableNetworkLogs: Boolean = false, appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(commonModule(enableNetworkLogs = enableNetworkLogs), platformModule())
    }

// called by iOS etc
fun initKoin() = initKoin(enableNetworkLogs = false) {}

fun commonModule(enableNetworkLogs: Boolean) = module {
    single { createJson() }
    single { createHttpClient(get(), get(), enableNetworkLogs = enableNetworkLogs) }
    single { CoroutineScope(Dispatchers.Default + SupervisorJob()) }
    databaseModule()
    pokedexModule()
    detailsModule()
}

fun createJson() = Json { isLenient = true; ignoreUnknownKeys = true }

fun createHttpClient(
    httpClientEngine: HttpClientEngine,
    json: Json,
    enableNetworkLogs: Boolean
): Ktorfit {
    val client = HttpClient(httpClientEngine) {
        defaultRequest { url("https://pokeapi.co/api/v2/") }
        install(ContentNegotiation) { json(json) }
        if (enableNetworkLogs) {
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.INFO
            }
        }
    }
    return Ktorfit.Builder().httpClient(client).build()
}

private fun Module.databaseModule() {
    single {
        PokemonDao(PokemonDatabase(get<DatabaseDriverFactory>().createDriver()).localPokemonQueries)
    }
}

private fun Module.pokedexModule() {
    single { injectPokedexService(get()) }
    single { PokedexRepository(get(), get()) }
}

private fun Module.detailsModule() {
    single { injectDetailsService(get()) }
    single { DetailsRepository(get()) }
}

private fun injectPokedexService(ktorfit: Ktorfit): PokedexService = ktorfit.create()

private fun injectDetailsService(ktorfit: Ktorfit): DetailsService = ktorfit.create()