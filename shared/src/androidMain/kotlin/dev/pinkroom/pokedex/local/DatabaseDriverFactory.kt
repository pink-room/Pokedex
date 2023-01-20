package dev.pinkroom.pokedex.local

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import dev.pinkroom.pokedex.PokemonDatabase.Companion.Schema

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver = AndroidSqliteDriver(Schema, context, "pokemon.db")
}