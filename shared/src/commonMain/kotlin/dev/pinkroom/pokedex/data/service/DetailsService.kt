package dev.pinkroom.pokedex.data.service

import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Path
import dev.pinkroom.pokedex.data.models.PokemonDetails
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

interface DetailsService {
    @GET("pokemon/{name}")
    suspend fun getDetails(@Path("name") name: String): PokemonDetails
}