package dev.pinkroom.pokedex.viewmodel.details

import dev.pinkroom.pokedex.data.models.PokemonDetails

data class DetailsState(
    val loading: Boolean = false,
    val pokemon: PokemonDetails? = null,
    val error: String? = null
)
