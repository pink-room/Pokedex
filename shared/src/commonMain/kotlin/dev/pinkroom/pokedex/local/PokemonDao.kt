package dev.pinkroom.pokedex.local

import dev.pinkroom.pokedex.LocalPokemon
import dev.pinkroom.pokedex.LocalPokemonQueries

class PokemonDao(private val queries: LocalPokemonQueries) {

    fun get(limit: Long, offset: Long): List<LocalPokemon> =
        queries.getAllPaging(limit, offset).executeAsList()

    fun deleteAll() {
        queries.deleteAll()
    }

    fun insert(list: List<LocalPokemon>) {
        queries.transaction {
            list.forEach { insert(it) }
        }
    }

    private fun insert(item: LocalPokemon) {
        queries.insert(item)
    }
}