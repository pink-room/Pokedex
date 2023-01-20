package dev.pinkroom.pokedex.utils

import dev.pinkroom.pokedex.LocalPokemon
import dev.pinkroom.pokedex.data.models.Pokemon

fun Pokemon.toPokemonModel() = LocalPokemon(name, url)

fun LocalPokemon.toPokemon() = Pokemon(name, url)