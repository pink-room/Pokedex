package dev.pinkroom.pokedex.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDetails(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    @SerialName("base_experience") val experience: Int,
    val types: List<TypeResponse>,
) {
    @Serializable
    data class TypeResponse(
        val slot: Int,
        val type: Type
    )

    @Serializable
    data class Type(val name: String)

    fun getNameCapitalized() =
        name.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }

    fun getImageUrl(): String {
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
    }
}