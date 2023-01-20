package dev.pinkroom.pokedex.local

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import dev.pinkroom.pokedex.PokemonDatabase.Companion.Schema

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver = NativeSqliteDriver(Schema, "pokemon.db")
}