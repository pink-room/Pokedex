package dev.pinkroom.pokedex.data.repository

import dev.pinkroom.pokedex.data.service.DetailsService
import org.koin.core.component.KoinComponent

class DetailsRepository(private val detailsService: DetailsService) : KoinComponent {

    suspend fun getDetails(name: String) = runCatching { detailsService.getDetails(name) }
}