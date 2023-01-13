package dev.pinkroom.pokedex.viewmodel.pokedex

sealed class PokedexEffect {
    data class NavigateToDetails(val name: String) : PokedexEffect()
}
