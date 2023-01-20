package dev.pinkroom.pokedex.data.service

import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Query
import dev.pinkroom.pokedex.data.models.PokemonResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

interface PokedexService {
    @GET("pokemon")
    suspend fun getPokemons(
        @Query("limit") limit: Long,
        @Query("offset") offset: Long
    ): PokemonResponse
}