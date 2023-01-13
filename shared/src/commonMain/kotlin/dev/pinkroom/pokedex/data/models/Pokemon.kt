package dev.pinkroom.pokedex.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Pokemon(
    val name: String,
    val url: String
) {

    fun getNameCapitalized() =
        name.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }

    fun getImageUrl(): String {
        val index = url.split("/".toRegex()).dropLast(1).last()
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$index.png"
    }
}